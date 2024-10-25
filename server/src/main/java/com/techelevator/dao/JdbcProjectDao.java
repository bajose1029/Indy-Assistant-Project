package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Project;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Component
public class JdbcProjectDao implements ProjectDao{

    private final JdbcTemplate template;
    private RowMapper<Project> MAPPER = new RowMapper<Project>() {
        @Override
        public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
            int projectId = rs.getInt("project_id");
            String projectName = rs.getString("project_name");
            LocalDate releaseDate = rs.getDate("release_date").toLocalDate();
            String description = rs.getString("description");
            boolean completed = rs.getBoolean("completed");

            Project project = new Project(projectId, projectName, releaseDate,description, completed);
            return project;
        }
    };

    public JdbcProjectDao(DataSource dataSource)
    {
        this.template = new JdbcTemplate(dataSource);
    }
    @Override
    public List<Project> getAllProjects() {
        List<Project> projects;
        String sql = "SELECT * FROM project ORDER BY release_date DESC, project_name;";

        try {
            projects = template.query(sql, MAPPER);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }
        return projects.isEmpty() ? null : projects;
    }

    @Override
    public List<Project> getAllArtistsProjects(int artistId) {
        List<Project> projects;
        String sql = "SELECT p.project_id, p.project_name, p.release_date, p.description, p.completed " +
                "FROM project p " + "JOIN artist_project ap ON p.project_id = ap.project_id " +
                "JOIN artist a ON ap.artist_id = a.artist_id " + "WHERE a.artist_id = ? " +
                "ORDER BY p.release_date DESC, p.project_name;";

        try {
            projects = template.query(sql, MAPPER, artistId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }
        return projects.isEmpty() ? null : projects;
    }
    @Override
    public Project getProjectById(int projectId) {
        String sql = "SELECT * FROM project WHERE project_id = ?";
        List<Project> projects;
        try {
            projects = template.query(sql, MAPPER, projectId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return projects.isEmpty() ? null : projects.get(0);
    }

    @Override
    public List<Project> getProjectsByReleaseDateRange(LocalDate start, LocalDate end) {
        List<Project> projects;
        String sql = "SELECT * FROM project " +
                "WHERE release_date BETWEEN ? AND ? " +
                "ORDER BY release_date DESC";

        try{
            projects = template.query(sql, MAPPER, start, end);
        } catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return projects.isEmpty() ? null : projects;
    }

    @Override
    public List<Project> getProjectsReleasedByArtist(String artistName) {
        List<Project> projects;
        String sql = "SELECT p.project_id, p.project_name, p.release_date, p.description, p.completed " +
                "FROM project p " +
                "JOIN artist_project ap ON ap.project_id = p.project_id " +
                "JOIN artist a ON a.artist_id = ap.artist_id " +
                "WHERE a.artist_name ILIKE ? " +
                "ORDER BY p.release_date DESC, p.project_name";

        try{
            projects = template.query(sql, MAPPER, artistName);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return projects.isEmpty() ? null : projects;
    }

    @Override
    public List<Project> getProjectsByManager(int managerId)
    {
        List<Project> projects;
        String sql = "SELECT p.project_id, p.project_name, p.release_date, p.description, " +
                "p.completed " +
                "FROM project p " +
                "JOIN artist_project ap ON ap.project_id = p.project_id " +
                "JOIN artist a ON a.artist_id = ap.artist_id " +
                "WHERE a.manager_id = ? " +
                "ORDER BY artist_name, release_date DESC;";

        try{
            projects = template.query(sql, MAPPER, managerId);
        }
        catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to the database or server");
        }
        return projects.isEmpty() ? null : projects;
    }

    @Override
    public Project createProject(Project project, int artistId) {
        int projectId;
        String sql = "INSERT INTO project(project_name, release_date, description, completed) " +
                "VALUES (?, ?, ?, ?) RETURNING project_id;";

        try {
            projectId = template.queryForObject(sql, int.class, project.getProjectName(), project.getReleaseDate(),
                    project.getDescription(), project.isCompleted());

            if(projectId > 0)
            {
                project.setProjectId(projectId);
                String associativeSql = "INSERT INTO artist_project(artist_id, project_id) " +
                        "VALUES (?, ?)";

                int rowsAffected = template.update(associativeSql, artistId, projectId);
                if(rowsAffected == 0)
                {
                    throw new DaoException("Expected at least one creation");
                }
            }
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return getProjectById(projectId);
    }

    @Override
    public Project updateProject(Project project) {
        Project updatedProject = null;
        String sql = "UPDATE project " +
                "SET project_name = ?, release_date = ?, description = ?, completed = ? " +
                "WHERE project_id = ?";

        try{
            int rowsAffected = template.update(sql, project.getProjectName(), project.getReleaseDate(),
                    project.getDescription(), project.isCompleted(), project.getProjectId());

            if(rowsAffected == 0)
            {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            else{
                updatedProject = getProjectById(project.getProjectId());
            }
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedProject;
    }

    @Override
    public int deleteProjectById(int projectId, int artistId) {
        String sql = "DELETE FROM project WHERE project_id = ?";
        String associativeSql = "DELETE FROM artist_project WHERE project_id = ? AND artist_id = ?";

        try {
            int rowsAffected = template.update(associativeSql, projectId, artistId);
            if(rowsAffected == 0)
            {
                throw new DaoException("Expected at least one deleted row");
            }

            return template.update(sql, projectId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }
    }


//    private static Project mapRowToProject(SqlRowSet rs) {
//        int projectId = rs.getInt("project_id");
//        String projectName = rs.getString("project_name");
//        LocalDate releaseDate = rs.getDate("release_date").toLocalDate();
//        String description = rs.getString("description");
//        Project project = new Project(projectId, projectName, releaseDate,description);
//        return project;
//    }
}

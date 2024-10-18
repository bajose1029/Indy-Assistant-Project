package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Manager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcManagerDao implements ManagerDao {

    private JdbcTemplate template;

    private RowMapper<Manager> MAPPER = new RowMapper<Manager>() {
        @Override
        public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
            int managerId = rs.getInt("manager_id");
            String name = rs.getString("manager_name");
            String emailAddress = rs.getString("manager_email_address");
            int userId = rs.getInt("user_id");
            String imageUrl = rs.getString("image_url");

            Manager manager = new Manager(managerId, name,emailAddress, userId, imageUrl);
            return manager;
        }
    };
    public JdbcManagerDao(DataSource dataSource)
    {
        this.template = new JdbcTemplate(dataSource);
    }
    @Override
    public List<Manager> getAllManagers() {
        List<Manager> managers;
        String sql = "SELECT * FROM manager ORDER BY manager_id;";

        try{
            managers = template.query(sql, MAPPER);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return managers.isEmpty() ? null : managers;
    }

    @Override
    public Manager getManagerById(int managerId) {
        List <Manager> managers;
        String sql = "SELECT * FROM manager WHERE manager_id = ?;";

        try{
            managers = template.query(sql, MAPPER, managerId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return managers.isEmpty() ? null : managers.get(0);
    }

    @Override
    public Manager getManagerByUserId(int userId)
    {
        List <Manager> managers;
        String sql = "SELECT * FROM manager WHERE user_id = ?;";

        try{
            managers = template.query(sql, MAPPER, userId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return managers.isEmpty() ? null : managers.get(0);
    }
    @Override
    public Manager getArtistsManager(String artistName) {
        List<Manager> managers;
        String sql = "SELECT m.manager_id, m.manager_name, m.manager_email_address, m.user_id, m.image_url " +
                "FROM manager m JOIN artist a ON a.manager_id = m.manager_id " +
                "WHERE a.artist_name ILIKE %?%";
        try{
            managers = template.query(sql, MAPPER, artistName);
        }
        catch(CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return managers.isEmpty() ? null : managers.get(0);
    }

    @Override
    public Manager getManagerByArtistId(int artistId) {
        List<Manager> managers;
        String sql = "SELECT m.manager_id, m.manager_name, m.manager_email_address, m.user_id, m.image_url " +
                "FROM manager m " +
                "JOIN artist a ON a.manager_id = m.manager_id " +
                "WHERE a.artist_id = ?";

        try{
            managers = template.query(sql, MAPPER, artistId);
        }
        catch(CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return managers.isEmpty() ? null : managers.get(0);
    }

    @Override
    public Manager createManager(Manager manager) {
        int managerId;

        String sql = "INSERT INTO manager(manager_name, manager_email_address, user_id, image_url) " +
                "VALUES(? ? ? ?) RETURNING manager_id;";

        try {
            managerId = template.queryForObject(sql, int.class, manager.getName(), manager.getEmailAddress(),
                    manager.getUserId(), manager.getImageUrl());
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return getManagerById(managerId);
    }

    @Override
    public Manager updateManager(Manager manager) {
        Manager updatedManager = null;
        String sql = "UPDATE manager " +
                "SET manager_name = ?, manager_email_address = ?, user_id = ?, image_url = ? " +
                "WHERE manager_id = ?;";

        try {
            int rowsAffected = template.update(sql, manager.getName(), manager.getEmailAddress(),
                    manager.getUserId(), manager.getImageUrl(), manager.getManagerId());

            if(rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            else {
                updatedManager = getManagerById(manager.getManagerId());
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

        return updatedManager;
    }

    @Override
    public int deleteManagerById(int managerId) {
        String sql = "DELETE FROM manager WHERE manager_id = ?;";

        try {
            return template.update(sql, managerId);
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
}

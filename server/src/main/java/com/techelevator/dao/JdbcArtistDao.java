package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Artist;
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
public class JdbcArtistDao implements ArtistDao{

    private final JdbcTemplate template;

    private RowMapper<Artist> MAPPER = new RowMapper<Artist>() {
        @Override
        public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
            int artistId = rs.getInt("artist_id");
            String name = rs.getString("artist_name");
            int managerId = rs.getInt("manager_id");
            String artistEmail = rs.getString("artist_email_address");
            int userId = rs.getInt("user_id");
            String imageUrl = rs.getString("image_url");
            String pro = rs.getString("pro");


            Artist artist = new Artist(artistId, name, managerId, artistEmail, userId, imageUrl, pro);
            return artist;
        }
    };
    public JdbcArtistDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artists;
        String sql = "SELECT * FROM artist ORDER BY artist_id;";

        try{
            artists = template.query(sql, MAPPER);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }
        return artists.isEmpty() ? null : artists;
    }

    @Override
    public Artist getArtistById(int artistId) {
        List<Artist> artist;
        String sql = "SELECT * FROM artist WHERE artist_id = ?";

        try{
            artist = template.query(sql, MAPPER, artistId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return artist.isEmpty() ? null : artist.get(0);
    }

    @Override
    public Artist getArtistByUserId(int userId){
        List<Artist> artist;
        String sql = "SELECT * FROM artist WHERE user_id = ?";

        try{
            artist = template.query(sql, MAPPER, userId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return artist.isEmpty() ? null : artist.get(0);
    }
    @Override
    public List<Artist> getManagersArtists(String name) {
        List<Artist> artists;
        String sql = "SELECT a.artist_id, a.artist_name, a.manager_id, a.artist_email_address, a.user_id, a.image_url, " +
                "a.pro " +
                "FROM artist a JOIN manager m ON m.manager_id = a.manager_id " +
                "WHERE m.manager_name ILIKE %?% " +
                "ORDER BY a.artist_name";

        try {
            artists = template.query(sql, MAPPER, name);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return artists.isEmpty() ? null : artists;
    }

    @Override
    public List<Artist> getArtistsOnAProject(String projectName) {
        List<Artist> artists;
        String sql = "SELECT a.artist_id, a.artist_name, a.manager_id, a.artist_email_address, a.user_id, a.image_url, " +
                "a.pro " +
                "FROM artist a " +
                "JOIN artist_project ap ON ap.artist_id = a.artist_id " +
                "JOIN project p ON p.project_id = ap.project_id " +
                "WHERE a.project_name ILIKE %?%";

        try {
            artists = template.query(sql,MAPPER, projectName);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return artists.isEmpty() ? null : artists;
    }

    @Override
    public List<Artist> getArtistsByManagerId(int managerId) {

        List<Artist> artists;
        String sql = "SELECT a.artist_id, a.artist_name, a.manager_id, a.artist_email_address, a.user_id, a.image_url, " +
                "a.pro " +
                "FROM artist a " +
                "JOIN manager m ON m.manager_id = a.manager_id " +
                "JOIN users u ON u.user_id = a.user_id " +
                "WHERE m.manager_id = ? AND u.enabled = true " +
                "ORDER BY a.artist_name";

        //I only want the artists that are enabled to show up for the managers
        try {
            artists = template.query(sql,MAPPER, managerId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }
        return artists.isEmpty() ? null : artists;
    }

    @Override
    public Artist createArtist(Artist artist) {

        Artist newArtist = null;
        String sql = "INSERT INTO artist(artist_name, manager_id, artist_email_address, user_id, image_url, pro) "
                + "VALUES(?, ?, ?, ?, ?, ?) RETURNING artist_id;";

        try {
            int artistId = template.queryForObject(sql, int.class, artist.getName(), artist.getManagerId(),
                    artist.getEmailAddress(), artist.getUserId(), artist.getImageUrl(), artist.getPro());
            newArtist = getArtistById(artistId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return newArtist;
    }

    @Override
    public Artist updateArtist(Artist artist) {
        Artist updatedArtist = null;
        String sql = "UPDATE artist " +
                "SET artist_name = ?, manager_id = ?, artist_email_address = ?, user_id = ?, image_url = ?, pro = ? " +
                "WHERE artist_id = ?;";

        try{
            int rowsAffected = template.update(sql, artist.getName(), artist.getManagerId(),
                    artist.getEmailAddress(), artist.getUserId(), artist.getImageUrl(), artist.getPro(),
                    artist.getArtistId());

            if(rowsAffected == 0) {
                throw  new DaoException("Zero rows affected, expected at least one");
            }
            else {
                updatedArtist = getArtistById(artist.getArtistId());
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

        return updatedArtist;
    }

    @Override
    public int deleteArtistById(int artistId) {
        String sql = "DELETE FROM artist WHERE artist_id = ?;";

        try {
            return template.update(sql, artistId);
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

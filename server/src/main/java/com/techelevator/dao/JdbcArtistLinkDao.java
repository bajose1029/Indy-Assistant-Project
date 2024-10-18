package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.ArtistLink;
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
public class JdbcArtistLinkDao implements ArtistLinkDao {

    private final JdbcTemplate template;

    public JdbcArtistLinkDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<ArtistLink> MAPPER = new RowMapper<ArtistLink>() {
        @Override
        public ArtistLink mapRow(ResultSet rs, int rowNum) throws SQLException {
            int linkId = rs.getInt("link_id");
            int artistId = rs.getInt("artist_id");
            String url = rs.getString("url");
            int siteTypeId = rs.getInt("site_type_id");

            ArtistLink artistLink = new ArtistLink(linkId, artistId, url, siteTypeId);
            return artistLink;
        }
    };
    @Override
    public List<ArtistLink> getAllLinks() {
        List<ArtistLink> links;
        String sql = "SELECT * FROM artist_link ORDER BY link_id;";

        try{
            links = template.query(sql, MAPPER);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return links.isEmpty() ? null : links;
    }

    @Override
    public ArtistLink getLinkByLinkId(int linkId) {
        List<ArtistLink> link;
        String sql = "SELECT * FROM artist_link WHERE link_id = ?;";

        try{
            link = template.query(sql, MAPPER, linkId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }
        return link.isEmpty() ? null : link.get(0);
    }
    @Override
    public List<ArtistLink> getLinksByArtistId(int artistId) {
        List<ArtistLink> links;
        String sql = "SELECT * FROM artist_link WHERE artist_id = ?;";

        try{
            links = template.query(sql, MAPPER, artistId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }
        return links.isEmpty() ? null : links;
    }

    @Override
    public ArtistLink createArtistLink(ArtistLink link) {
        int linkId;
        String sql = "INSERT INTO artist_link(artist_id, url, site_type_id) " +
                "VALUES (? ? ?) RETURNING link_id;";

        try{
            linkId = template.queryForObject(sql, int.class, link.getArtistId(), link.getUrl(), link.getSiteTypeId());
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }

        return getLinkByLinkId(linkId);
    }

    @Override
    public ArtistLink updateArtistLink(ArtistLink link) {

        return null;
    }

    @Override
    public int deleteArtistLinkById(int artistId) {
        return 0;
    }
}

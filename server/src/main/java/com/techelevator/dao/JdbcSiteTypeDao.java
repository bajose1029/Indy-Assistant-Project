package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.SiteType;
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
public class JdbcSiteTypeDao implements SiteTypeDao{

    private final JdbcTemplate template;

    public JdbcSiteTypeDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<SiteType> MAPPER = new RowMapper<SiteType>() {
        @Override
        public SiteType mapRow(ResultSet rs, int rowNum) throws SQLException {
            int siteTypeId = rs.getInt("site_type_id");
            String siteName = rs.getString("site_name");

            SiteType siteType = new SiteType(siteTypeId, siteName);
            return siteType;
        }
    };


    public List<SiteType> getAllSiteTypes() {
        List<SiteType> siteTypes;
        String sql = "SELECT * FROM site_type ORDER BY site_type_id";

        try {
            siteTypes = template.query(sql, MAPPER);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }
        return siteTypes;
    }

    public SiteType getSiteTypeById(int siteTypeId){
        List<SiteType> siteType;
        String sql = "SELECT * FROM site_type WHERE site_type_id = ?";

        try{
            siteType = template.query(sql, MAPPER, siteTypeId);
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }
        return siteType.isEmpty() ? null : siteType.get(0);
    }
    @Override
    public SiteType createSiteType(SiteType siteType) {
        int siteTypeId;

        String sql = "INSERT INTO site_type(site_name) " +
                "VALUES(?) RETURNING site_type_id";

        try{
            siteTypeId = template.queryForObject(sql, int.class, siteType.getSiteName());
        }
        catch (CannotGetJdbcConnectionException e)
        {
            throw new DaoException("Unable to connect to database ", e);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DaoException("Data integrity violation", e);
        }
        return getSiteTypeById(siteTypeId);
    }

    @Override
    public SiteType updateSiteType(SiteType siteType) {
        SiteType updatedSiteType = null;
        String sql = "UPDATE site_type " +
                "SET site_name = ? " +
                "WHERE site_type_id = ?;";

        try {
            int rowsAffected = template.update(sql, siteType.getSiteName(), siteType.getSiteTypeId());

            if(rowsAffected == 0){
                throw  new DaoException("Zero rows affected, expected at least one");
            }
            else {
                updatedSiteType = getSiteTypeById(siteType.getSiteTypeId());
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

        return updatedSiteType;
    }

    @Override
    public int deleteSiteType(int siteTypeId) {
        String sql = "DELETE FROM site_type WHERE site_type_id = ?";

        try{
            return template.update(sql, siteTypeId);
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

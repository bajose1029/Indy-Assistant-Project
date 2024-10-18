package com.techelevator.dao;

import com.techelevator.model.SiteType;

import java.util.List;

public interface SiteTypeDao {
    List<SiteType> getAllSiteTypes();
    SiteType createSiteType(SiteType siteType);
    SiteType updateSiteType(SiteType siteType);
    int deleteSiteType(int siteTypeId);
}

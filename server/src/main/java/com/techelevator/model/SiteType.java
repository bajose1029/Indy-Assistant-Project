package com.techelevator.model;

public class SiteType {
    private int siteTypeId;
    private String siteName;

    public SiteType(int siteTypeId, String site_name) {
        this.siteTypeId = siteTypeId;
        this.siteName = site_name;
    }

    public int getSiteTypeId() {
        return siteTypeId;
    }

    public void setSiteTypeId(int siteTypeId) {
        this.siteTypeId = siteTypeId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}

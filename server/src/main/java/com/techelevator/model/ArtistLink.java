package com.techelevator.model;

import java.util.Objects;

public class ArtistLink {

    private int linkId;
    private int artistId;
    private String url;
    private int siteTypeId;

    public ArtistLink(int linkId, int artistId, String url, int siteTypeId) {
        this.linkId = linkId;
        this.artistId = artistId;
        this.url = url;
        this.siteTypeId = siteTypeId;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSiteTypeId() {
        return siteTypeId;
    }

    public void setSiteTypeId(int siteTypeId) {
        this.siteTypeId = siteTypeId;
    }

    @Override
    public String toString() {
        return "Link {" +
                "linkId = " + linkId +
                ", artistId = " + artistId +
                ", url = '" + url + '\'' +
                ", siteTypeId = " + siteTypeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistLink link = (ArtistLink) o;

        return linkId == link.linkId && artistId == link.artistId && siteTypeId == link.siteTypeId &&
                Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkId, artistId, url, siteTypeId);
    }
}

package com.techelevator.model;

import java.util.Objects;

public class Artist {
    private int artistId;
    private String name;
    private int managerId = 0;
    private String emailAddress;
    private int userId;
    private String imageUrl;
    private String pro;

    public Artist(int artistId, String artistName, int managerId, String emailAddress, int userId,
                  String imageURL, String pro) {
        this.artistId = artistId;
        this.name = artistName;
        this.managerId = managerId;
        this.emailAddress = emailAddress;
        this.userId = userId;
        this.imageUrl = imageURL;
        this.pro = pro;
    }

    public Artist() {
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return name;
    }

    public void setArtistName(String artistName) {
        this.name = artistName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    @Override
    public String toString() {
        return "Artist {" +
                "artistId = " + artistId +
                ", artistName = '" + name + '\'' +
                ", managerId = " + managerId +
                ", emailAddress = '" + emailAddress + '\'' +
                ", userId = '" + userId + '\'' +
                ", imageURL = '" + imageUrl + '\'' +
                ", pro = '" + pro + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return artistId == artist.artistId && managerId == artist.managerId && userId == artist.userId &&
                Objects.equals(name, artist.name) && Objects.equals(emailAddress, artist.emailAddress)
                && Objects.equals(imageUrl, artist.imageUrl) && Objects.equals(pro, artist.pro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistId, name, managerId, emailAddress, userId, imageUrl, pro);
    }
}

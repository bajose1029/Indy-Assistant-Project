package com.techelevator.model;

import java.util.Objects;

public class Manager {
    private int managerId;
    private String name;
    private String emailAddress;
    private int userId;
    private String imageUrl;

    public Manager(int managerId, String name, String emailAddress, int userId, String imageUrl) {
        this.managerId = managerId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.userId = userId;
        this.imageUrl = imageUrl;
    }

    public Manager() {
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Manager {" +
                "managerId = " + managerId +
                ", name = '" + name + '\'' +
                ", emailAddress = '" + emailAddress + '\'' +
                ", userId = '" + userId + '\'' +
                ", imageUrl = '" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return managerId == manager.managerId && userId == manager.userId && Objects.equals(name, manager.name) &&
                Objects.equals(emailAddress, manager.emailAddress)
                && Objects.equals(imageUrl, manager.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerId, name, emailAddress, userId, imageUrl);
    }
}

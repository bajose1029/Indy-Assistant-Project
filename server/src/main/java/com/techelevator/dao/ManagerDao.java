package com.techelevator.dao;

import com.techelevator.model.Manager;

import java.util.List;

public interface ManagerDao {
    List<Manager> getAllManagers();
    Manager getManagerById(int managerId);
    Manager getManagerByArtistId(int artistId);
    Manager getManagerByUserId(int userId);
    Manager getArtistsManager(String artistName);
    Manager createManager(Manager manager);
    Manager updateManager(Manager manager);
    int deleteManagerById(int managerId);
}

package com.techelevator.dao;

import com.techelevator.model.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectDao {
    List<Project> getAllProjects();
    List<Project> getAllArtistsProjects(int artistId);
    Project getProjectById(int projectId);
    List<Project> getProjectsByReleaseDateRange(LocalDate start, LocalDate end);
    List<Project> getProjectsReleasedByArtist(String artistName);
    List<Project> getProjectsByManager(int managerId);
    Project createProject(Project project, int artistId);
    Project updateProject(Project project);
    int deleteProjectById(int projectId, int artistId);
}

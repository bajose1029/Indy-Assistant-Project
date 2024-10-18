package com.techelevator.model;

import java.time.LocalDate;
import java.util.Objects;

public class Project {
    private int projectId;
    private String projectName;
    private LocalDate releaseDate;
    private String description;
    private boolean completed = false;

    public Project(int projectId, String projectName, LocalDate releaseDate, String description, boolean completed) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.releaseDate = releaseDate;
        this.description = description;
        this.completed = completed;
    }

    public Project() {
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public String getDescription() {
        return description;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Project {" +
                "projectId = " + projectId +
                ", projectName = '" + projectName + '\'' +
                ", releaseDate = " + releaseDate +
                ", completed = " + completed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId == project.projectId && Objects.equals(projectName, project.projectName) &&
                Objects.equals(releaseDate, project.releaseDate) && Objects.equals(completed, project.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, releaseDate, completed);
    }
}

package com.techelevator.controller;

import com.techelevator.dao.ArtistDao;
import com.techelevator.dao.ManagerDao;
import com.techelevator.dao.ProjectDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Artist;
import com.techelevator.model.Manager;
import com.techelevator.model.Project;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/projects")
@PreAuthorize("isAuthenticated()")
public class ProjectController {

    private final ProjectDao projectDao;
    private final UserDao userDao;
    private final ArtistDao artistDao;
    private final ManagerDao managerDao;
    public ProjectController(ProjectDao projectDao, UserDao userDao, ArtistDao artistDao, ManagerDao managerDao){
        this.projectDao = projectDao;
        this.userDao = userDao;
        this.artistDao = artistDao;
        this.managerDao = managerDao;
    }

    @GetMapping
    public List<Project> getAllProjects(Principal principal){
        List<Project> projects = null;
        User user = userDao.getUserByUsername(principal.getName());
        if(user.getRole().equals("ROLE_ADMIN"))
        {
            projects = projectDao.getAllProjects();
        }
        else if (user.isEnabled())
        {
            Artist artist = artistDao.getArtistByUserId(user.getId());
            if(artist == null)
            {
                Manager manager = managerDao.getManagerByUserId(user.getId());
                if(manager == null)
                {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User account can't be found. Please create" +
                            "an Artist or Manager account to see list.");
                }
                projects = projectDao.getProjectsByManager(manager.getManagerId());
            }
            else
            {
                projects = projectDao.getAllArtistsProjects(artist.getArtistId());
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account can't be found or has been deactivated. " +
                    "Please contact admin for assistance.");
        }
        return projects;
    }


    @GetMapping("/{projectId}")
    public Project getProjectByProjectId(@PathVariable int projectId){
        return projectDao.getProjectById(projectId);
    }

    @GetMapping("/artists/{artistId}")
    public List<Project> getArtistsProjects(@PathVariable int artistId) {
        return projectDao.getAllArtistsProjects(artistId);
    }

    @PostMapping
    public Project addArtistsProject(@RequestBody Project project, Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        if(!user.isEnabled())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has been deactivated. Please reach out to " +
                    "Admin to reactivate.");
        }
        Artist artist = artistDao.getArtistByUserId(user.getId());
        int artistId = artist.getArtistId();
        return projectDao.createProject(project, artistId);
    } //When adding a project, I need to know the user, so I can attach the project to the specific artist and add it to the associative table

    @PutMapping("/{projectId}")
    public Project updateArtistsProject(@RequestBody Project project, @PathVariable int projectId, Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        if(!user.isEnabled())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has been deactivated. Please reach out to " +
                    "Admin to reactivate.");
        }
        project.setProjectId(projectId);
        return projectDao.updateProject(project);
    }

    @DeleteMapping("/{projectId}")
    public int deleteArtistsProject(@PathVariable int projectId, Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        if(!user.isEnabled())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has been deactivated. Please reach out to " +
                    "Admin to reactivate.");
        }
        Artist artist = artistDao.getArtistByUserId(user.getId());
        int artistId = artist.getArtistId();
        return projectDao.deleteProjectById(projectId,artistId);
    }

}

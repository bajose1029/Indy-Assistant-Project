package com.techelevator.controller;

import com.techelevator.dao.ArtistDao;
import com.techelevator.dao.ManagerDao;
import com.techelevator.dao.ProjectDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Artist;
import com.techelevator.model.Manager;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/managers")
@PreAuthorize("isAuthenticated()")
public class ManagerController {

    private final ProjectDao projectDao;
    private final UserDao userDao;
    private final ArtistDao artistDao;
    private final ManagerDao managerDao;
    public ManagerController(ProjectDao projectDao, UserDao userDao, ArtistDao artistDao, ManagerDao managerDao) {

        this.projectDao = projectDao;
        this.userDao = userDao;
        this.artistDao = artistDao;
        this.managerDao = managerDao;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Manager> getAllManagers() {
        return managerDao.getAllManagers();
    }

    @GetMapping("/{id}")
    public Manager getManagerById(@PathVariable int id, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());

        if(!user.isEnabled()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has been deactivated. Please reach out to " +
                    "Admin to reactivate.");
        }

        return managerDao.getManagerById(id);
    }

    @GetMapping("/artist/{artistId}")
    public Manager getManagerByArtistId(@PathVariable int artistId, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());

        if (!user.isEnabled()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has been deactivated. Please reach out to " +
                    "Admin to reactivate.");
        }

        return managerDao.getManagerByArtistId(artistId);

    }
    @GetMapping("/user/{userId}")
    public Manager getManagerByUserId(@PathVariable int userId, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());

        if(!user.isEnabled())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has been deactivated. Please reach out to " +
                    "Admin to reactivate.");
        }

        return managerDao.getManagerByUserId(userId);
    }
    @PostMapping
    @PreAuthorize("permitAll()")
    public Manager addManager(@RequestBody Manager manager) {
        return managerDao.createManager(manager);
    }

    @PutMapping("/{managerId}")
    public Manager updateManager(@RequestBody Manager manager, @PathVariable int managerId, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        if(!user.isEnabled()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has been deactivated. Please reach out to " +
                    "Admin to reactivate.");
        }

        manager.setManagerId(managerId);
        return managerDao.updateManager(manager);
    }

    @DeleteMapping("/{managerId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public int deleteManager(@PathVariable int managerId) {
        return managerDao.deleteManagerById(managerId);
    }
}

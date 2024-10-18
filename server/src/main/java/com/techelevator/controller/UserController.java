package com.techelevator.controller;

import com.techelevator.dao.ArtistDao;
import com.techelevator.dao.ProjectDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Artist;
import com.techelevator.model.Project;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(path = "/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    private final ProjectDao projectDao;
    private final UserDao userDao;
    private final ArtistDao artistDao;


    public UserController(ProjectDao projectDao, UserDao userDao, ArtistDao artistDao) {
        this.projectDao = projectDao;
        this.userDao = userDao;
        this.artistDao = artistDao;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam Optional<Boolean> enabled){
        if(enabled.isPresent()){
            return userDao.getEnabledUsers(enabled.get());
        }
        return userDao.getUsers();
    }

    @GetMapping("/{usernameOrId}")
    public User getUserById(@PathVariable String usernameOrId)
    {
        try {
            int id = Integer.parseInt(usernameOrId);
            return userDao.getUserById(id);
        } catch (NumberFormatException e) {
            return userDao.getUserByUsername(usernameOrId);
        }

    }

    @PutMapping("/{usernameOrId}")
    public User toggleOnOrOffUserAccount(@RequestParam boolean enabled, @PathVariable String usernameOrId){
        User user= null;
        try{
            int id = Integer.parseInt(usernameOrId);
            user = userDao.getUserById(id);
            return userDao.turnOnOrOffAccount(user, enabled);
        }
        catch (NumberFormatException e){
            user = userDao.getUserByUsername(usernameOrId);
            return userDao.turnOnOrOffAccount(user, enabled);
        }
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userDao.createUser(user);
    }
}

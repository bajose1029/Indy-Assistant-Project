package com.techelevator.controller;

import com.techelevator.dao.ArtistDao;
import com.techelevator.dao.ManagerDao;
import com.techelevator.dao.ProjectDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Artist;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/artists")
@PreAuthorize("isAuthenticated()")
public class ArtistController {

    private final ProjectDao projectDao;
    private final UserDao userDao;
    private final ArtistDao artistDao;
    private final ManagerDao managerDao;
    public ArtistController(ProjectDao projectDao, UserDao userDao, ArtistDao artistDao, ManagerDao managerDao) {
        this.projectDao = projectDao;
        this.userDao = userDao;
        this.artistDao = artistDao;
        this.managerDao = managerDao;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Artist> getAllArtists() {
        return artistDao.getAllArtists();
    }

    @GetMapping("/user/{userId}")
    public Artist getArtistByUserId(@PathVariable int userId, Principal principal){
        User user = userDao.getUserByUsername(principal.getName());

        if(!user.isEnabled())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has been deactivated. Please reach out to " +
                    "Admin to reactivate.");
        }
        return artistDao.getArtistByUserId(userId);
    }
    @GetMapping("/{id}")
    public List<Artist> getArtistById(@PathVariable int id, Principal principal) {
        List<Artist> artist = new ArrayList<>();
        User user = userDao.getUserByUsername(principal.getName());
        if(!user.isEnabled())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has been deactivated. Please reach out to " +
                    "Admin to reactivate.");
        }

        if(user.getType().equalsIgnoreCase("Artist") || user.getType().equalsIgnoreCase("Admin"))
        {
            Artist idArtist = artistDao.getArtistById(id);
            artist.add(idArtist);
        }
        else if(user.getType().equalsIgnoreCase("Manager")) {
            artist = artistDao.getArtistsByManagerId(id);
        }

        return artist;

    }

    @PostMapping
    public Artist addArtist(@RequestBody Artist artist) {
        return artistDao.createArtist(artist);
    }

    @PutMapping("/{artistId}")
    public Artist updateArtist(@RequestBody Artist artist, @PathVariable int artistId, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        if(!user.isEnabled())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has been deactivated. Please reach out to " +
                    "Admin to reactivate.");
        }
        artist.setArtistId(artistId);

        return artistDao.updateArtist(artist);
    }

    @DeleteMapping("/{artistId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public int deleteArtist(@PathVariable int artistId) {
        return artistDao.deleteArtistById(artistId);
    }
}

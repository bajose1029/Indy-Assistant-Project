package com.techelevator.dao;

import com.techelevator.model.Artist;

import java.util.List;

public interface ArtistDao {
    List<Artist> getAllArtists();
    Artist getArtistById(int artistId);
    List<Artist> getManagersArtists(String name);
    List<Artist> getArtistsOnAProject(String projectName);
    Artist createArtist(Artist artist);
    Artist updateArtist(Artist artist);
    int deleteArtistById(int artistId);
    List<Artist> getArtistsByManagerId(int managerId);
    Artist getArtistByUserId(int userId);
}

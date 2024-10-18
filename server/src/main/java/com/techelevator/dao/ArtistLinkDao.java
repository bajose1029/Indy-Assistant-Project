package com.techelevator.dao;

import com.techelevator.model.ArtistLink;

import java.util.List;

public interface ArtistLinkDao {
    List<ArtistLink> getAllLinks();

    ArtistLink getLinkByLinkId(int linkId);

    List<ArtistLink> getLinksByArtistId(int artistId);

    ArtistLink createArtistLink(ArtistLink link);
    ArtistLink updateArtistLink(ArtistLink link);
    int deleteArtistLinkById(int artistId);
}

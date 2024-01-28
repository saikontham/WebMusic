package com.webstreaming.webstreaming.entities;

import jakarta.persistence.OneToOne;

import java.util.List;

public class PlaylistRequest {
    private String playlistName;
    private List<Integer> songIds;


    public PlaylistRequest() {
        // Default constructor
    }

    public PlaylistRequest(String playlistName, List<Integer> songIds) {
        this.playlistName = playlistName;
        this.songIds = songIds;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Integer> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Integer> songIds) {
        this.songIds = songIds;
    }
}

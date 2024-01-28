package com.webstreaming.webstreaming.services;

import com.webstreaming.webstreaming.entities.Playlist;

import java.util.List;

public interface PlaylistSerivce {
    public void addPlaylist(Playlist playlist);
    List<Playlist> getPlaylistsByCustomerId(Integer id);
    public List<Playlist> fetchPlaylist();
}

package com.webstreaming.webstreaming.services;


import com.webstreaming.webstreaming.entities.Playlist;
import com.webstreaming.webstreaming.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListServiceImplementation implements PlaylistSerivce{

    @Autowired
    private PlaylistRepository playlistRepository;
    @Override
    public void addPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public List<Playlist> getPlaylistsByCustomerId(Integer id) {
        // Implement the logic to retrieve playlists by user ID from the repository
        return playlistRepository.findByCustomerId(id);
    }


    @Override
    public List<Playlist> fetchPlaylist() {
        return playlistRepository.findAll();
    }
}

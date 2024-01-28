package com.webstreaming.webstreaming.services;

import com.webstreaming.webstreaming.entities.Song;
import com.webstreaming.webstreaming.repositories.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    Logger logger= LoggerFactory.getLogger(SongService.class);

    @Autowired
    private SongRepository songRepository;

    public Song getSongById(Long id) {
        Optional<Song> songOptional = songRepository.findById(id);
        return songOptional.orElse(null);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }


    public boolean addSong(Song song) {
        if(song!=null &&  isUniqueSong(song)) {
            songRepository.save(song);
           logger.info("song added successfully");
            return true;
        }
        else{
            logger.info("Song already exists");
            return false;
        }
    }

    private boolean isUniqueSong(Song song) {
        return !songRepository.existsSongByArtistAndAlbum(song.getArtist(),song.getAlbum());
    }

    public boolean deleteSong(Long id) {
        try {
            // Check if the song exists
            if (songRepository.existsById(id)) {
                songRepository.deleteById(id);
                return true; // Indicate success
            } else {
                return false; // Indicate failure, song not found
            }
        } catch (Exception e) {
            // Handle any other exception that occurs during the deletion process
            // Log the exception or handle it based on your requirements
            return false; // Indicate failure
        }
    }

    public boolean updateSong(Long id, Song updatedSong) {
        try {
            // Check if the song with the given id exists
            if (songRepository.existsById(id)) {
                // Set the id of the updated song to match the existing song
                updatedSong.setId(id);
                songRepository.save(updatedSong);
                return true; // Indicate success
            } else {
                return false; // Indicate failure, song not found
            }
        } catch (Exception e) {
            // Handle any other exception that occurs during the update process

            return false; // Indicate failure
        }
    }

//    public List<Song> getSongsByIds(List<Long> selectedSongIds) {
//        return songRepository.findAll(selectedSongIds);
//    }
}

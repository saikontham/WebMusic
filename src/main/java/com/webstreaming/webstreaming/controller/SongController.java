package com.webstreaming.webstreaming.controller;

import com.webstreaming.webstreaming.entities.Song;
import com.webstreaming.webstreaming.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> addSong(@RequestBody Song song) {
        try {
            boolean addedSuccessfully = songService.addSong(song);

            if (addedSuccessfully) {
                return ResponseEntity.ok("Song added successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to add the song. It may already exist.");
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request.");
        }
    }
    @PostMapping("/deletesong/{id}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        try {
            boolean deletesong = songService.deleteSong(id);

            if (deletesong) {
                return ResponseEntity.ok("Song deleted successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to delete the song. Song not found or deletion unsuccessful.");
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request.");
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> updateSong(@PathVariable Long id, @RequestBody Song updatedSong) {
        try {
            boolean updatedSuccessfully = songService.updateSong(id, updatedSong);

            if (updatedSuccessfully) {
                return ResponseEntity.ok("Song updated successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to update the song. Song not found or update unsuccessful.");
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request.");
        }
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Song song = songService.getSongById(id);

        if(song != null) {
            return ResponseEntity.ok(song);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();

        if (!songs.isEmpty()) {
            return ResponseEntity.ok(songs);
        } else {
            return ResponseEntity.noContent().build();
        }
    }




}

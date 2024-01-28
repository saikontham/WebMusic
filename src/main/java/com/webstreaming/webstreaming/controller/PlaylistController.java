package com.webstreaming.webstreaming.controller;


import com.webstreaming.webstreaming.entities.Customer;
import com.webstreaming.webstreaming.entities.Playlist;
import com.webstreaming.webstreaming.entities.Song;
import com.webstreaming.webstreaming.model.PlaylistRequest;
import com.webstreaming.webstreaming.services.CustomerService;
import com.webstreaming.webstreaming.services.PlaylistSerivce;
import com.webstreaming.webstreaming.services.SongService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("*")
public class PlaylistController {
    @Autowired
    private SongService songService;

    @Autowired
    private PlaylistSerivce playlistSerivce;
    @Autowired
    private CustomerService userService;

    //    @GetMapping(value = "/createplaylist", produces = "application/json")
//    public ResponseEntity<List<Songs>> createPlaylist(Model model)
//    {
//        List<Songs> songsList = songService.viewallsongs();
//
//        return ResponseEntity.ok().body(songsList);
//    }
    @GetMapping(value = "/createplaylist")
    public ResponseEntity<List<Song>> createPlaylist(Model model) {
        List<Song> songsList = songService.getAllSongs();
        return ResponseEntity.ok().body(songsList);
    }

    //    @PostMapping("/addplaylist")
//    public ResponseEntity<String> addPlaylist(@RequestBody Playlist playlist)
//    {
//        playlistSerivce.addPlaylist(playlist);
//        List<Songs> songslist = playlist.getSongs();
//
//        for (Songs s:songslist)
//        {
//            s.getPlaylist().add(playlist);
//            songService.updateSong(s);
//        }
//        return new ResponseEntity<>("Added",HttpStatus.OK);
//    }
    @PostMapping("/addplaylist")
    public ResponseEntity<String> addPlaylist(@RequestBody PlaylistRequest playlistRequest, HttpSession session) {
        try {
            // Your implementation
            Playlist playlist=new Playlist();
//            playlistRequest.setPlaylistName(playlistRequest.getPlaylistName());
            System.out.println("Playlist Name "+playlistRequest.getPlaylistName());
            // Retrieve the songs based on the provided song IDs
            List<Song> songs = playlistRequest.getSongIds().stream()
                    .map(songId->songService.getSongById(Long.valueOf(songId)))
                    .collect(Collectors.toList());
//        if (songs != null) {
//            for (Songs s : songs) {
//                if (s.getPlaylists() == null) {
//                    s.setPlaylists(new ArrayList<>());
//                }
//                s.getPlaylists().add(playlistRequest);
//                songService.updateSong(s);
//            }
//        }
            playlist.setSongs(songs);
            playlist.setName(playlistRequest.getPlaylistName());
            // Get the user ID from the session or wherever it's stored
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String email=authentication.getName();
            Customer customer= userService.findCustomerByEmail(email);
            playlist.setCustomer(customer);
            System.out.println(email);
            playlistSerivce.addPlaylist(playlist);
            customer.getPlaylists().add(playlist);
            userService.updateCustomer(customer);

            return new ResponseEntity<>("Added", HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            e.printStackTrace(); // Example: Print the stack trace to the console

            return new ResponseEntity<>("Failed to add playlist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/viewplaylist/{id}")
//    @PreAuthorize("hasAnyAuthority('admin','user')")
//    public ResponseEntity<List<Playlist>> viewPlaylist(@PathVariable Integer id ,Model model)
//    {
//        List<Playlist> playlists=playlistSerivce.getPlaylistsByCustomerId(id);
//        System.out.println(playlists);
//        System.out.println(userService.getCustomer(id));
//
//        if (playlists!=null)
//        {
//            return ResponseEntity.ok().body(playlists);
//        }
//        else {
//            return ResponseEntity.badRequest().body(playlists);
//        }
//    }
@GetMapping("/viewplaylist/{id}")
@PreAuthorize("hasAnyAuthority('admin', 'user')")
public ResponseEntity<List<Playlist>> viewPlaylist(@PathVariable Integer id, Authentication authentication) {
    // Get the email of the currently logged-in user
    String userEmail = authentication.getName();

    // Retrieve the customer based on the email
    Customer loggedinCustomer = userService.findCustomerByEmail(userEmail);

    // Check if the logged-in user has the authority to view the specified customer's playlists
    if (loggedinCustomer.getId().equals(id) || authentication.getAuthorities().contains(new SimpleGrantedAuthority("admin"))) {
        List<Playlist> playlists = playlistSerivce.getPlaylistsByCustomerId(id);
        System.out.println(playlists);
        System.out.println(loggedinCustomer);

        if (playlists != null) {
            return ResponseEntity.ok().body(playlists);
        } else {
            return ResponseEntity.badRequest().body(playlists);
        }
    } else {
        // The logged-in user doesn't have the authority to view playlists for the specified customer
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}


//    @GetMapping("/viewplaylist")
//    public ResponseEntity<List<Playlist>> viewPlaylist()
//    {
//        List<Playlist> playlists=playlistSerivce.fetchPlaylist();
//
//        return new ResponseEntity<>(playlists, HttpStatus.OK);
//    }

}

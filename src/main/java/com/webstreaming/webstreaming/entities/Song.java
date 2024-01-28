package com.webstreaming.webstreaming.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String artist;
    private String genre;
    private String album;
    private String link;
    private int likes;

    @JsonIgnore
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", album='" + album + '\'' +
                ", link='" + link + '\'' +
                ", likes=" + likes +
                '}';
    }
}

package com.webstreaming.webstreaming.repositories;

import com.webstreaming.webstreaming.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    boolean existsSongByArtistAndAlbum(String artist,String album);

//    List<Song> findAll(List<Long> selectedSongIds);
}

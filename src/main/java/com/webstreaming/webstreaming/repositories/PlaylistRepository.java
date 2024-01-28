package com.webstreaming.webstreaming.repositories;

import com.webstreaming.webstreaming.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    List<Playlist> findByCustomerId(Integer id);
}

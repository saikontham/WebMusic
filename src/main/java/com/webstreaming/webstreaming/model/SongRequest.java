package com.webstreaming.webstreaming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongRequest {
    private String title;
    private String artist;

    // getters and setters

    // ...
}
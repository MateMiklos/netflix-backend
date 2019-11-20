package com.codecool.videoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recommendation {

    private Long id;
    private int rating;
    private String comment;
    private Long videoId;

}

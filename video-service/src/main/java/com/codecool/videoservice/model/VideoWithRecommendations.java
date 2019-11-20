package com.codecool.videoservice.model;

import com.codecool.videoservice.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class VideoWithRecommendations {

    private Video video;
    private List<Recommendation> recommendations;
}

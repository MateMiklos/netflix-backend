package com.codecool.videoservice.service;

import com.codecool.videoservice.entity.Video;
import com.codecool.videoservice.model.Recommendation;
import com.codecool.videoservice.model.VideoWithRecommendations;
import com.codecool.videoservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recommendation-service.url}")
    private String baseUrl;

    public Recommendation getRecommendationsByVideoId(Long videoId) {
        Recommendation recommendations = restTemplate
                .getForEntity(baseUrl + videoId, Recommendation.class).getBody();
        return recommendations;
    }

    public Video getVideoById(Long id) {
        return videoRepository.getVideoById(id);
    }

    public VideoWithRecommendations getVideoPlusRecommendationsById(Long id) {

        VideoWithRecommendations videoWithRecommendations = VideoWithRecommendations.builder()
                .video(getVideoById(id))
                .recommendations(Arrays.asList(getRecommendationsByVideoId(id)))
                .build();

        return videoWithRecommendations;
    }
}

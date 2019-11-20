package com.codecool.videoservice.service;

import com.codecool.videoservice.entity.Video;
import com.codecool.videoservice.model.Recommendation;
import com.codecool.videoservice.model.VideoWithRecommendations;
import com.codecool.videoservice.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recommendation-service.url}")
    private String baseUrl;

    public List<Recommendation> getRecommendationsByVideoId(Long videoId) {
        String url = baseUrl + "/" + videoId.toString();

        ResponseEntity<List<Recommendation>> recommendations =
                restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Recommendation>>() {});

        return recommendations.getBody();
    }

    public Video getVideoById(Long id) {
        return videoRepository.getVideoById(id);
    }

    public VideoWithRecommendations getVideoPlusRecommendationsById(Long id) {
        Video videoById = getVideoById(id);
        log.info("=== VIDEO ===");
        log.info(videoById.toString());

        List<Recommendation> recommendationsByVideoId = getRecommendationsByVideoId(id);
        log.info("=== RECOMMENDATIONS ===");
        log.info(recommendationsByVideoId.toString());

        VideoWithRecommendations videoWithRecommendations = VideoWithRecommendations.builder()
                .video(videoById)
                .recommendations(recommendationsByVideoId)
                .build();

        return videoWithRecommendations;
    }
}

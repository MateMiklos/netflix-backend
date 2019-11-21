package com.codecool.recommendationservice.service;

import com.codecool.recommendationservice.entity.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    public void createNewRecommendationForVideo(Long videoId, String comment, int rating) {

        Recommendation recommendation = Recommendation.builder()
                .videoId(videoId)
                .rating(rating)
                .comment(comment)
                .build();

        recommendationRepository.save(recommendation);
    }

    public void updateRecommendation() {

    }
}

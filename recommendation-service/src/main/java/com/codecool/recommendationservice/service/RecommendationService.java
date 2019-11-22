package com.codecool.recommendationservice.service;

import com.codecool.recommendationservice.entity.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    public void createNewRecommendationForVideo(Long videoId, Map<String, String> recommendation) {

        Recommendation recommendationToSave = Recommendation.builder()
                .videoId(videoId)
                .rating(Integer.parseInt(recommendation.get("rating")))
                .comment(recommendation.get("comment"))
                .build();

        recommendationRepository.save(recommendationToSave);
    }

    public void updateRecommendation(Recommendation recommendationToUpdate, Recommendation recommendation) {
        recommendationToUpdate.setComment(recommendation.getComment());
        recommendationToUpdate.setRating(recommendation.getRating());

        recommendationRepository.save(recommendationToUpdate);
    }
}

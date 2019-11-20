package com.codecool.recommendationservice.service;

import com.codecool.recommendationservice.entity.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecommendationService {

    public String getJsonFromGivenUrl(String videoId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/videos/";
        String response = restTemplate.getForEntity(url + "id/" + videoId, String.class).getBody();
        return response;
    }


    @Autowired
    private RecommendationRepository recommendationRepository;

    public void createNewRecommendationForVideo(Long videoId) {


        Recommendation recommendation = Recommendation.builder()
                .videoId(videoId)
                .rating(5)
                .comment("")
                .build();

        recommendationRepository.save(recommendation);
    }
}

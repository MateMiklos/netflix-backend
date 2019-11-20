package com.codecool.recommendationservice.controller;

import com.codecool.recommendationservice.entity.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import com.codecool.recommendationservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @GetMapping("/{videoId}")
    public List<Recommendation> getAllRecommendationByVideoId(@PathVariable("videoId") Long videoId) {
        return recommendationRepository.getAllRecommendationByVideoId(videoId);
    }

    @PostMapping("/{videoId}")
    public void addNewRecommendationToGivenVideoById(@PathVariable("videoId") Long videoId,
                                                     @RequestParam String comment,
                                                     @RequestParam int rating) {
        recommendationService.createNewRecommendationForVideo(videoId, comment, rating);
    }
}

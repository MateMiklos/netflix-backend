package com.codecool.recommendationservice.controller;

import com.codecool.recommendationservice.entity.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import com.codecool.recommendationservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/recommendation/{recommendationId}")
    public Recommendation getRecommendationById(@PathVariable("recommendationId") Long recommendationId) {
        return recommendationRepository.getRecommendationById(recommendationId);
    }

    @PostMapping("/{videoId}")
    public void addNewRecommendationToGivenVideoById(@PathVariable("videoId") Long videoId,
                                                     @RequestBody Map<String, String> recommendation) {
        recommendationService.createNewRecommendationForVideo(videoId, recommendation);
    }

    @PutMapping("/update/{id}")
    public void updateRecommendation(@PathVariable("id") Long id, @RequestBody Recommendation recommendation) {
        Recommendation recommendationToUpdate = getRecommendationById(id);
        recommendationService.updateRecommendation(recommendationToUpdate, recommendation);
    }
}

package com.codecool.videoservice.controller;

import com.codecool.videoservice.entity.Video;
import com.codecool.videoservice.model.Recommendation;
import com.codecool.videoservice.model.VideoWithRecommendations;
import com.codecool.videoservice.repository.VideoRepository;
import com.codecool.videoservice.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/videos")
@Slf4j
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoService videoService;

    @GetMapping("/")
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @GetMapping("/{id}")
    public VideoWithRecommendations getVideoById(@PathVariable("id") Long id) {
        return videoService.getVideoPlusRecommendationsById(id);
    }

    @PostMapping("/add/{videoId}")
    public void addNewRecommendationToGivenVideoById(@PathVariable("videoId") Long videoId,
                                                     @RequestBody Map<String , String> recommendation) {
        log.info(recommendation.toString());
        videoService.createNewRecommendationForVideo(videoId, recommendation);
    }

    @PutMapping("/update-video/{id}")
    public Video updateVideo(@PathVariable("id") Long id, @RequestBody Video video) {
        Video videoToUpdate = getVideoById(id).getVideo();
        return videoService.updateVideo(videoToUpdate, video);
    }

    @PutMapping("update-recommendation/{id}")
    public void updateRecommendation(@PathVariable("id") Long id, @RequestBody Recommendation recommendation) {
        videoService.updateRecommendation(recommendation);
    }


}

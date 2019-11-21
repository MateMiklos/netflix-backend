package com.codecool.videoservice.controller;

import com.codecool.videoservice.entity.Video;
import com.codecool.videoservice.model.Recommendation;
import com.codecool.videoservice.model.VideoWithRecommendations;
import com.codecool.videoservice.repository.VideoRepository;
import com.codecool.videoservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoService videoService;

    @GetMapping("/")
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public VideoWithRecommendations getVideoById(@PathVariable("id") Long id) {
        return videoService.getVideoPlusRecommendationsById(id);
    }

//    @PostMapping("/{videoId}/{recommendationId}")
//    public Recommendation updateVideoAndItsRecommendations(@PathVariable("videoId") Long videoId,
//                                                           @PathVariable("recommendationId") Long recommendationId,
//                                                           @RequestParam String name,
//                                                           @RequestParam String url,
//                                                           @RequestParam String comment,
//                                                           @RequestParam int rating) {
//        Recommendation recommendationToUpdate = videoService.
//                updateVideoAndItsRecommendations(videoId, recommendationId, name, url, comment, rating);
//
//        return recommendationToUpdate;
//    }

    @PutMapping("/update-video/{id}")
    public Video updateVideo(@PathVariable("id") Long id, @RequestBody Video video) {
        Video videoToUpdate = getVideoById(id).getVideo();
        return videoService.updateVideo(videoToUpdate, video);
    }

    @PutMapping("update-recommendation/{id}")
    public void updateRecommendation(@RequestBody Recommendation recommendation) {
        videoService.updateRecommendation(recommendation);
    }
}

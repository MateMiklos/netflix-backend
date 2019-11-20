package com.codecool.videoservice.controller;

import com.codecool.videoservice.entity.Video;
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
}

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

import java.util.List;
import java.util.Map;

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

//    public Recommendation updateVideoAndItsRecommendations(Long videoId, Long recommendationId,
//                                                 String name, String url, String comment, int rating) {
//
//        Video video = getVideoById(videoId);
//        video.setName(name);
//        video.setUrl(url);
//        videoRepository.save(video);
//
//        List<Recommendation> recommendations = getRecommendationsByVideoId(videoId);
//        for (Recommendation recommendation : recommendations) {
//            if (recommendation.getId().equals(recommendationId)) {
//                recommendation.setComment(comment);
//                recommendation.setRating(rating);
//                return recommendation;
//            }
//        }
//        return null;
//    }

    public Video updateVideo(Video videoToUpdate, Video video) {
        videoToUpdate.setName(video.getName());
        videoToUpdate.setUrl(video.getUrl());

        videoRepository.save(videoToUpdate);

        return videoToUpdate;
    }

    public void updateRecommendation(Recommendation recommendationToUpdate) {
//        restTemplate.put(baseUrl + "/update/" + recommendationToUpdate.getId().toString(), recommendationToUpdate);

        String url = baseUrl + "/update/" + recommendationToUpdate.getId().toString();

        ResponseEntity<List<Recommendation>> recommendations =
                restTemplate.exchange(url, HttpMethod.POST, null,
                new ParameterizedTypeReference<List<Recommendation>>() {});
    }

    public void createNewRecommendationForVideo(Long videoId, Map<String, String> recommendation) {
        log.info("THIS IS THE RECOMMENDATION I WANT TO SAVE");
        log.info(recommendation.toString());
//        restTemplate.postForEntity(baseUrl + "/add/" + videoId, recommendation, String.class);

        String url = baseUrl + "/update/" + recommendation.get("id");

        ResponseEntity<List<Recommendation>> recommendations =
                restTemplate.exchange(url, HttpMethod.POST, null,
                        new ParameterizedTypeReference<List<Recommendation>>() {});


    }
}

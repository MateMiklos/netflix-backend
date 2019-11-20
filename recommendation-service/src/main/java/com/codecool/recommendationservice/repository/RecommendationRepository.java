package com.codecool.recommendationservice.repository;

import com.codecool.recommendationservice.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    @Query("select r from Recommendation r where r.videoId = :videoId")
    List<Recommendation> getAllRecommendationByVideoId(@Param("videoId") Long videoId);
}

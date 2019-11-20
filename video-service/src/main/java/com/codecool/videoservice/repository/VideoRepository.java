package com.codecool.videoservice.repository;

import com.codecool.videoservice.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("select v from Video v where v.id = :videoId")
    Video getVideoById(@Param("videoId") Long videoId);
}

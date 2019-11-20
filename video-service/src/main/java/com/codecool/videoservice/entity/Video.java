package com.codecool.videoservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String url;

//    @EqualsAndHashCode.Exclude
//    private List<Long> recommendationIds;
}

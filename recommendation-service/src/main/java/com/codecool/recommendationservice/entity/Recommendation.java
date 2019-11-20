package com.codecool.recommendationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Recommendation {

    @Id
    @GeneratedValue
    private Long id;

    @Min(value = 1)
    @Max(value = 5)
    private int rating;

    private String comment;

    private Long videoId;
}

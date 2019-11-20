package com.codecool.recommendationservice;

import com.codecool.recommendationservice.entity.Recommendation;
import com.codecool.recommendationservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@SpringBootApplication
public class RecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendationServiceApplication.class, args);
	}

	@Autowired
	private RecommendationRepository recommendationRepository;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	@Profile("production")
	public CommandLineRunner init() {
		return args -> {

			Recommendation commentForWotlk1 = Recommendation.builder()
					.comment("Wohhhoooo")
					.rating(4)
					.videoId(1L)
					.build();

			Recommendation commentForWotlk2 = Recommendation.builder()
					.comment("Arthas rulez!!!")
					.rating(5)
					.videoId(1L)
					.build();



			recommendationRepository.saveAll(Arrays.asList(commentForWotlk1, commentForWotlk2));

		};
	}
}

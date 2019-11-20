package com.codecool.videoservice;

import com.codecool.videoservice.entity.Video;
import com.codecool.videoservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaServer
@EnableSwagger2
public class VideoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoServiceApplication.class, args);
	}

	@Autowired
	private VideoRepository videoRepository;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@Profile("production")
	public CommandLineRunner init() {
		return args -> {

			Video video1 = Video.builder()
					.name("Wrath of the Lich King Cinematic")
					.url("https://www.youtube.com/watch?v=BCr7y4SLhck")
					.build();

			Video video2 = Video.builder()
					.name("Legacy of the Void Cinematic")
					.url("https://www.youtube.com/watch?v=M_XwzBMTJaM")
					.build();

			Video video3 = Video.builder()
					.name("Diablo IV. Cinematic")
					.url("https://www.youtube.com/watch?v=0SSYzl9fXOQ")
					.build();

			videoRepository.saveAll(Arrays.asList(video1, video2, video3));
		};
	}
}

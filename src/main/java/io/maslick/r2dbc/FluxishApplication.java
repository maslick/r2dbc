package io.maslick.r2dbc;

import io.maslick.r2dbc.db.FeedRepo;
import io.maslick.r2dbc.dto.Datus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class FluxishApplication {
	public static void main(String[] args) {
		SpringApplication.run(FluxishApplication.class, args);
	}

	private final FeedRepo repo;

	@PostConstruct
	public void post() {
		repo.saveAll(Arrays.asList(
				Datus.builder().feed("hello").timestamp(System.currentTimeMillis()).build(),
				Datus.builder().feed("world").timestamp(System.currentTimeMillis()).build()
		));

		repo.findAllByFeed("hello").forEach(d -> {
			log.info("findAll - " + d);
		});
	}
}

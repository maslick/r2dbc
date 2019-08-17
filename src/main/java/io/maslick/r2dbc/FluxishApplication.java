package io.maslick.r2dbc;

import io.maslick.r2dbc.db.FeedRepo;
import io.maslick.r2dbc.dto.Feed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.function.DatabaseClient;

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
	private final DatabaseClient client;

	@PostConstruct
	public void post() {
		repo.saveAll(Arrays.asList(
				Feed.builder().name("hello").timestamp(System.currentTimeMillis()).build(),
				Feed.builder().name("world").timestamp(System.currentTimeMillis()).build()
		)).log().subscribe();
		repo.findAllByFeed("hello").subscribe(d -> {
			log.info("findAll - " + d);
		});

		client.select()
				.from(Feed.class)
				.fetch()
				.all().subscribe(d -> log.info(d.toString()));
		System.out.println("#################");
	}
}

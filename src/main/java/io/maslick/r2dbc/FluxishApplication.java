package io.maslick.r2dbc;

import io.maslick.r2dbc.db.FeedRepo;
import io.maslick.r2dbc.dto.Feed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.function.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.BaseStream;

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
	public void post() throws URISyntaxException {
		List<Feed> sampleFeeds = Arrays.asList(
				Feed.builder().name("hello").timestamp(System.currentTimeMillis()).build(),
				Feed.builder().name("world").timestamp(System.currentTimeMillis()).build()
		);
		getSchema().flatMap(sql -> client.execute().sql(sql).fetch().rowsUpdated()).block();
		repo.saveAll(sampleFeeds)
				.then(repo.findAllByFeed("hello").as(Mono::just))
				.flatMapMany(Flux::from)
				.doOnNext(d -> log.info("findByName - " + d))
		.then(client.select().from(Feed.class).fetch().all().as(Mono::just))
				.flatMapMany(Flux::from)
				.subscribe(d -> log.info(d.toString()));
	}

	private Mono<String> getSchema() throws URISyntaxException {
		Path path = Paths.get(ClassLoader.getSystemResource("schema.sql").toURI());
		return Flux
				.using(() -> Files.lines(path), Flux::fromStream, BaseStream::close)
				.reduce((line1, line2) -> line1 + "\n" + line2);
	}
}

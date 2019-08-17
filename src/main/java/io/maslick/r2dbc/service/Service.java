package io.maslick.r2dbc.service;

import io.maslick.r2dbc.db.FeedRepo;
import io.maslick.r2dbc.dto.Feed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class Service implements IService {

	private final FeedRepo repo;

	@Override
	public Mono<Feed> retrieve(Integer id) {
		log.debug("Retrieving name by id: {}", id);
		return Mono.justOrEmpty(repo.findById(id));
	}

	@Override
	public Flux<Feed> retrieve(String name) {
		log.debug("Retrieving feeds by name: {}", name);
		return Flux.fromIterable(repo.findAllByName(name));
	}

	@Override
	public Flux<Feed> retrieveAll() {
		log.debug("Retrieving all feeds");
		return Flux.fromIterable(repo.findAll());
	}

	@Override
	public Mono<Feed> save(String name) {
		log.debug("Saving name: {}", name);
		return Mono.just(repo.save(new Feed(null, name, System.currentTimeMillis())));
	}
}

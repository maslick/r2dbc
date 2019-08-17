package io.maslick.r2dbc.service;

import io.maslick.r2dbc.db.FeedRepo;
import io.maslick.r2dbc.dto.Datus;
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
	public Mono<Datus> retrieve(Integer id) {
		log.debug("Retrieving feed by id: {}", id);
		return Mono.justOrEmpty(repo.findById(id));
	}

	@Override
	public Flux<Datus> retrieve(String feed) {
		log.debug("Retrieving feeds by name: {}", feed);
		return Flux.fromIterable(repo.findAllByFeed(feed));
	}

	@Override
	public Flux<Datus> retrieveAll() {
		log.debug("Retrieving all feeds");
		return Flux.fromIterable(repo.findAll());
	}

	@Override
	public Mono<Datus> save(String feed) {
		log.debug("Saving feed: {}", feed);
		return Mono.just(repo.save(new Datus(null, feed, System.currentTimeMillis())));
	}
}

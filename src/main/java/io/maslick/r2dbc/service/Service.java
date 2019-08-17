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
		log.debug("Retrieving feed by id: {}", id);
		return repo.findById(id);
	}

	@Override
	public Flux<Feed> retrieve(String name) {
		log.debug("Retrieving feeds by name: {}", name);
		return repo.findAllByFeed(name);
	}

	@Override
	public Flux<Feed> retrieveAll() {
		log.debug("Retrieving all feeds");
		return repo.findAll();
	}

	@Override
	public Mono<Feed> save(String name) {
		log.debug("Saving feed: {}", name);
		return repo.save(new Feed(null, name, System.currentTimeMillis()));
	}
}

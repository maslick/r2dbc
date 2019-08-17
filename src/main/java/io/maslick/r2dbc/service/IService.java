package io.maslick.r2dbc.service;

import io.maslick.r2dbc.dto.Feed;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IService {
	Mono<Feed> retrieve(Integer id);
	Flux<Feed> retrieve(String name);
	Flux<Feed> retrieveAll();
	Mono<Feed> save(String name);
}

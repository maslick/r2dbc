package io.maslick.r2dbc.service;

import io.maslick.r2dbc.dto.Datus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IService {
	Mono<Datus> retrieve(Integer id);
	Flux<Datus> retrieve(String feed);
	Flux<Datus> retrieveAll();
	Mono<Datus> save(String feed);
}

package io.maslick.r2dbc.db;

import io.maslick.r2dbc.dto.Feed;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FeedRepo extends R2dbcRepository<Feed, Integer> {
	@Query("SELECT * FROM feeds WHERE name = $1")
	Flux<Feed> findAllByFeed(String feed);
}

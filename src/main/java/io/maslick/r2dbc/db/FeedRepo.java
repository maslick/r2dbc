package io.maslick.r2dbc.db;

import io.maslick.r2dbc.dto.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepo extends JpaRepository<Feed, Integer> {
	List<Feed> findAllByName(String name);
}

package io.maslick.r2dbc.db;

import io.maslick.r2dbc.dto.Datus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepo extends JpaRepository<Datus, Integer> {
	List<Datus> findAllByFeed(String feed);
}

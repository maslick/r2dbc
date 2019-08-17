package io.maslick.r2dbc.api;

import io.maslick.r2dbc.dto.Feed;
import io.maslick.r2dbc.service.IService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/feeds")
@RequiredArgsConstructor
public class RestApi {

	private final IService service;

	@GetMapping(path = "/id/{id}")
	public Publisher<Feed> getById(@PathVariable Integer id) {
		return service.retrieve(id);
	}

	@GetMapping(path = "/name/{name}")
	public Publisher<Feed> getByName(@PathVariable String name) {
		return service.retrieve(name);
	}

	@GetMapping(path = "/")
	public Publisher<Feed> getAllFeeds() {
		return service.retrieveAll();
	}

	@PostMapping(path = "/new")
	public Publisher<Feed> post(@RequestBody String feed) {
		return service.save(feed);
	}
}
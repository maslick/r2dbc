package io.maslick.r2dbc;

import io.maslick.r2dbc.db.FeedRepo;
import io.maslick.r2dbc.dto.Feed;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class FluxishApplicationTests {
	@LocalServerPort private int port;
	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Autowired private FeedRepo repo;

	@Before
	public void before() {
		repo.deleteAll();
	}

	@Test
	public void test() {
		ResponseEntity<Feed> resp1 = add();
		Assert.assertEquals(APPLICATION_JSON, resp1.getHeaders().getContentType());
		Assert.assertEquals(OK, resp1.getStatusCode());

		String url = "http://localhost:" + port + "/feeds/name/hello";
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<Feed[]> resp2 = restTemplate.exchange(url, GET, new HttpEntity<>(headers), Feed[].class);

		Assert.assertEquals(APPLICATION_JSON, resp2.getHeaders().getContentType());
		Assert.assertEquals(OK, resp2.getStatusCode());
		Assert.assertEquals("hello", resp2.getBody()[0].getName());
	}

	private ResponseEntity<Feed> add() {
		String data = "hello";
		String url = "http://localhost:" + port + "/feeds/new";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(data, headers);
		return restTemplate.exchange(url, POST, entity, Feed.class);
	}
}

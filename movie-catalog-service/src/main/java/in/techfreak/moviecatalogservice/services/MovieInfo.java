package in.techfreak.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import in.techfreak.moviecatalogservice.model.CatalogItem;
import in.techfreak.moviecatalogservice.model.Movie;
import in.techfreak.moviecatalogservice.model.Rating;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
			})
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = this.restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
				Movie.class);
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}

	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie Name not found", "", rating.getRating());
	}
}

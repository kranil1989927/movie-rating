package in.techfreak.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import in.techfreak.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;
	
	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		return new Movie(movieId, "3 Idiots","The Best Entertainer");
	}

}

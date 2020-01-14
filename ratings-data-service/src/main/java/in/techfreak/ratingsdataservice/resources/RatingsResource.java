package in.techfreak.ratingsdataservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.techfreak.ratingsdataservice.model.Rating;
import in.techfreak.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingdata")
public class RatingsResource {

	@GetMapping("/movies/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@GetMapping("/user/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.initData(userId);
		return userRating;
	}
}

package in.techfreak.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.techfreak.moviecatalogservice.model.CatalogItem;
import in.techfreak.moviecatalogservice.model.UserRating;
import in.techfreak.moviecatalogservice.services.MovieInfo;
import in.techfreak.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	@Autowired
	private MovieInfo movieInfo;

	@Autowired
	private UserRatingInfo userRatingInfo;

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating userRating = userRatingInfo.getUserRating(userId);
		return userRating.getRatings().stream().map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
	}

}

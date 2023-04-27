package ch.ilv.scrapbook.rating;

import ch.ilv.scrapbook.base.MessageResponse;
import ch.ilv.scrapbook.dataaccess.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository repository;

    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public List<Rating> getRatings() {
        return repository.findAll();
    }

    public Rating getRating(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Rating.class));
    }
    public Rating updateRating(Rating rating, Long id) {
        return repository.findById(id)
                .map(ratingOrig -> {
                    ratingOrig.setStars(rating.getStars());
                    ratingOrig.setScrapbook(rating.getScrapbook());
                    return repository.save(ratingOrig);
                })
                .orElseGet(() -> repository.save(rating));
    }
    public Rating insertRating(Rating rating) {
        return repository.save(rating);
    }
    public MessageResponse deleteRating(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Rating" + id + " deleted");
    }


}

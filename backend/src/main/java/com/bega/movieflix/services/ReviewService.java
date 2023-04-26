package com.bega.movieflix.services;

import com.bega.movieflix.dtos.ReviewDTO;
import com.bega.movieflix.dtos.UserDTO;
import com.bega.movieflix.entities.Movie;
import com.bega.movieflix.entities.Review;
import com.bega.movieflix.entities.User;
import com.bega.movieflix.repositories.MovieRepository;
import com.bega.movieflix.repositories.ReviewRepository;
import com.bega.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    final ReviewRepository reviewRepository;
    final MovieRepository movieRepository;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    public ReviewDTO postReview(User user, ReviewDTO reviewDTO) {
        var newReview = new Review();
        Optional<Movie> movieOptional = movieRepository.findById(reviewDTO.getMovieId());
        Movie movie = movieOptional.orElseThrow(() -> new ResourceNotFoundException("Movie not found."));

        newReview.setText(reviewDTO.getText());
        newReview.setUser(user);
        newReview.setMovie(movie);
        newReview = reviewRepository.save(newReview);

        UserDTO userDTO = new UserDTO(user);
        reviewDTO.setUser(userDTO);
        reviewDTO.setId(newReview.getId());
        reviewDTO.setMovieId(reviewDTO.getMovieId());
        return reviewDTO;
    }
}

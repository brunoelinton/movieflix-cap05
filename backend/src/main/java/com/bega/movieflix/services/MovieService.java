package com.bega.movieflix.services;

import com.bega.movieflix.dtos.MovieDTO;
import com.bega.movieflix.dtos.ReviewDTO;
import com.bega.movieflix.entities.Movie;
import com.bega.movieflix.repositories.GenreRepository;
import com.bega.movieflix.repositories.MovieRepository;
import com.bega.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    final MovieRepository movieRepository;
    final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        Movie movie = movieOptional.orElseThrow(() -> new ResourceNotFoundException("Movie not found."));
        return new MovieDTO(movie);
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findReviewsByMovieId(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        Movie movie = movieOptional.orElseThrow(() -> new ResourceNotFoundException("Movie not found."));
        List<ReviewDTO> reviewsListDto = new ArrayList<>();
        movie.getReviews().forEach(review -> reviewsListDto.add(new ReviewDTO(review)));
        return reviewsListDto;
    }

    public Page<MovieDTO> findAllMoviesByGenre(Long genreId, Pageable pageable) {
        if (genreId > 0L) {
            genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Genre not found."));
            return movieRepository.findByGenreId(genreId, pageable).map(movie -> new MovieDTO(movie.getId(), movie.getTitle(), movie.getSubTitle(), movie.getYear(), movie.getImgUrl()));
        } else {
            return movieRepository.findByGenreId(pageable).map(movie -> new MovieDTO(movie.getId(), movie.getTitle(), movie.getSubTitle(), movie.getYear(), movie.getImgUrl()));
        }
    }
}

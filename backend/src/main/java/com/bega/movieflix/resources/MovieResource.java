package com.bega.movieflix.resources;

import com.bega.movieflix.dtos.MovieDTO;
import com.bega.movieflix.dtos.ReviewDTO;
import com.bega.movieflix.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    final MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.findById(id));
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewsByMovieId(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.findReviewsByMovieId(id));
    }

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findAllMoviesByGenre(
            @RequestParam(name = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        System.out.println("genreId = " + genreId);
        return ResponseEntity.ok().body(movieService.findAllMoviesByGenre(genreId, pageable));
    }
}

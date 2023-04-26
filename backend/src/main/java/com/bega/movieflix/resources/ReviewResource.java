package com.bega.movieflix.resources;

import com.bega.movieflix.dtos.ReviewDTO;
import com.bega.movieflix.entities.User;
import com.bega.movieflix.services.AuthService;
import com.bega.movieflix.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {

    final AuthService authService;
    final ReviewService reviewService;

    public ReviewResource(AuthService authService, ReviewService reviewService) {
        this.authService = authService;
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> postReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        User user = authService.authenticated();
        ReviewDTO newReview = reviewService.postReview(user, reviewDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newReview.getId()).toUri();
        return ResponseEntity.created(uri).body(newReview);
    }
}

package com.bega.movieflix.repositories;

import com.bega.movieflix.entities.Genre;
import com.bega.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT obj FROM Movie obj WHERE " +
            "obj.genre.id = :genreId " +
            "ORDER BY obj.title")
    Page<Movie> findByGenreId(Long genreId, Pageable pageable);

    @Query("SELECT obj FROM Movie obj " +
            "ORDER BY obj.title")
    Page<Movie> findByGenreId(Pageable pageable);
}

package com.bega.movieflix.services;

import com.bega.movieflix.dtos.GenreDTO;
import com.bega.movieflix.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreDTO> findAll() {
        return genreRepository.findAll().stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
    }
}

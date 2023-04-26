package com.bega.movieflix.dtos;

import com.bega.movieflix.entities.Genre;
import com.bega.movieflix.entities.Movie;
import com.bega.movieflix.entities.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MovieDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String subTitle;
    @NotEmpty
    private Integer year;
    @NotEmpty
    private String imgUrl;
    @NotEmpty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String synopsis;
    @NotEmpty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GenreDTO genre;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ReviewDTO> reviews = new HashSet<>();

    public MovieDTO(){}

    public MovieDTO(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis, Genre genre, Set<Review> reviews) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.year = year;
        this.imgUrl = imgUrl;
        this.synopsis = synopsis;
        this.genre = new GenreDTO(genre);
        reviews.forEach(review -> this.reviews.add(new ReviewDTO(review)));
    }

    public MovieDTO(Movie entity) {
        id = entity.getId();
        title = entity.getTitle();
        subTitle = entity.getSubTitle();
        year = entity.getYear();
        imgUrl = entity.getImgUrl();
        synopsis = entity.getSynopsis();
        genre = new GenreDTO(entity.getGenre());
        reviews = null;
    }

    public MovieDTO(Long id, String title, String subTitle, Integer year, String imgUrl) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.year = year;
        this.imgUrl = imgUrl;
        synopsis = null;
        genre = null;
        reviews = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public Set<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}

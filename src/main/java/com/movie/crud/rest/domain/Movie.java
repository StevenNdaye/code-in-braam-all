package com.movie.crud.rest.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty
    private Long id;

    @Column(name = "name")
    @JsonProperty
    private String name;

    @Column(name = "rating")
    @JsonProperty
    private int rating;

    @Column(name = "availability")
    @JsonProperty
    private Long availability;

    @Column(name = "review", length = 500)
    @JsonProperty
    private String review;

    @Column(name = "thumb")
    @JsonProperty
    private String thumb;

    public Movie() {
    }

    @JsonCreator
    public Movie(@JsonProperty("name") String name, @JsonProperty("rating") int rating, @JsonProperty("availability") Long availability,
                 @JsonProperty("review") String review, @JsonProperty("thumb") String thumb) {
        this.name = name;
        this.rating = rating;
        this.availability = availability;
        this.review = review;
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public Long getAvailability() {
        return availability;
    }

    public String getReview() {
        return review;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setAvailability(Long availability) {
        this.availability = availability;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }
}

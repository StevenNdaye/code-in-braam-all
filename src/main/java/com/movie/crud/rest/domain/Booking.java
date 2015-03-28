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
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty
    private Long id;
    @Column(name = "movieId")
    @JsonProperty
    private Long movieId;
    @Column(name = "date")
    @JsonProperty
    private String date;
    @Column(name = "quantity")
    @JsonProperty
    private int quantity;
    @Column(name = "movieName")
    @JsonProperty
    private String movieName;

    public Booking() {
    }

    @JsonCreator
    public Booking(@JsonProperty("movieId") Long movieId, @JsonProperty("date") String date, @JsonProperty("quantity") int quantity, @JsonProperty("movieName") String movieName) {
        this.movieId = movieId;
        this.date = date;
        this.quantity = quantity;
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }
}

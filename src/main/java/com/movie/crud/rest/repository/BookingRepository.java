package com.movie.crud.rest.repository;

import com.movie.crud.rest.domain.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long>{
    Booking findByMovieId(Long movieId);
}

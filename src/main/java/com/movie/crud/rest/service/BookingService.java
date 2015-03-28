package com.movie.crud.rest.service;

import com.movie.crud.rest.domain.Booking;
import com.movie.crud.rest.repository.BookingRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

import static com.movie.crud.rest.utils.Utils.ifNotNull;

@Service
public class BookingService {

    private static final String BOOKING_DELETED = "Booking deleted!";
    private static final String INVALID_BOOKING = "Invalid booking!";
    private BookingRepository bookingRepository;

    @Inject
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Collection<Booking> getBookings() {
        return (Collection<Booking>) bookingRepository.findAll();
    }

    public Booking getBooking(Long bookingId) {
        return bookingRepository.findOne(bookingId);
    }


    public Booking createBooking(Booking booking) {
        Booking returnedBooking = bookingRepository.findByMovieId(booking.getMovieId());
        if (returnedBooking == null) {
            return bookingRepository.save(booking);
        }
        return returnedBooking;
    }

    public String deleteBooking(Long bookingId) {
        if (bookingRepository.exists(bookingId)) {
            bookingRepository.delete(bookingId);
            return BOOKING_DELETED;
        }
        return INVALID_BOOKING;
    }

    public Booking updateBooking(Long bookingId, Booking booking) {
        Booking existingBooking = bookingRepository.findOne(bookingId);
        if (existingBooking == null) {
            return createBooking(booking);
        }
        return updateExistingBooking(booking, existingBooking);
    }

    private Booking updateExistingBooking(Booking booking, Booking existingBooking) {
        existingBooking.setMovieName(ifNotNull(booking.getMovieName(), existingBooking.getMovieName()));
        existingBooking.setMovieId(ifNotNull(booking.getMovieId(), existingBooking.getMovieId()));
        existingBooking.setDate(ifNotNull(booking.getDate(), existingBooking.getDate()));
        existingBooking.setQuantity(ifNotNull(booking.getQuantity(), existingBooking.getQuantity()));

        return bookingRepository.save(existingBooking);
    }
}

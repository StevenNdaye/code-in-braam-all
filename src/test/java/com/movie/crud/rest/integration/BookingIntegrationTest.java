package com.movie.crud.rest.integration;

import com.movie.crud.rest.AbstractIntegrationTest;
import com.movie.crud.rest.domain.Booking;
import com.movie.crud.rest.repository.BookingRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BookingIntegrationTest extends AbstractIntegrationTest {

    @Inject
    private BookingRepository bookingRepository;
    private String url;

    @Before
    public void setUp() throws Exception {
        url = resourceUrl("/");
    }

    @Test
    public void shouldGetBookings() throws Exception {
        Booking booking = new Booking(10L, "2015-03-26", 3, "movieName");
        bookingRepository.save(booking);

        ResponseEntity<Booking[]> response = restTemplate.getForEntity(url + "bookings", Booking[].class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        Collection<Booking> bookings = asList(response.getBody());
        assertThat(bookings.iterator().next().getMovieId(), is(booking.getMovieId()));
    }

    @Test
    public void shouldGetABooking() throws Exception {
        Booking booking = new Booking(10L, "2015-03-26", 3, "movieName");

        Booking savedBooking = bookingRepository.save(booking);

        ResponseEntity<Booking> response = restTemplate.getForEntity(url + "bookings/{bookingId}", Booking.class, savedBooking.getId());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void shouldCreateABooking() throws Exception {
        Booking booking = new Booking(10L, "2015-03-26", 3, "movieName");
        ResponseEntity<Booking> createdBooking = restTemplate.postForEntity(url + "bookings", booking, Booking.class);

        Booking retrievedBooking = bookingRepository.findOne(createdBooking.getBody().getId());

        assertThat(createdBooking.getBody().getId(), is(retrievedBooking.getId()));

        assertThat(createdBooking.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void shouldUpdateABooking() throws Exception {
        Booking booking = new Booking(10L, "2015-03-26", 3, "movieName");
        Booking savedBooking = bookingRepository.save(booking);
        savedBooking.setMovieId(9L);
        restTemplate.put(url + "bookings/{bookingId}", savedBooking, savedBooking.getId());

        assertThat(savedBooking.getMovieId(), is(9L));
    }

    @Test
    public void shouldDeleteABooking() throws Exception {
        Booking booking = new Booking(10L, "2015-03-26", 3, "movieName");
        Booking savedBooking = bookingRepository.save(booking);

        restTemplate.delete(url + "bookings/{bookingId}", savedBooking.getId());

        Booking retrievedBooking = bookingRepository.findOne(savedBooking.getId());

        assertNull(retrievedBooking);
    }

    @After
    public void tearDown() throws Exception {
        bookingRepository.deleteAll();
    }
}

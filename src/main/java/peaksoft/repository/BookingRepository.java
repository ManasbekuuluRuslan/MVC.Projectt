package peaksoft.repository;

import peaksoft.entity.Booking;

import java.util.List;

public interface BookingRepository {
    List<Booking> getAllBooking();
}

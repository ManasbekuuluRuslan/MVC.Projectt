package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Booking;
import peaksoft.repository.BookingRepository;

import java.util.List;
@Repository
@Transactional
public class BookingRepositoryImpl implements BookingRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Booking> getAllBooking() {
        return entityManager.createQuery(
                "select b from Booking b").getResultList();
    }
}

package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.entity.Customer;
import peaksoft.repository.CustomerRepository;

import java.util.List;
@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public boolean existsByEmail(String email) {
        String jpql = "select count(c) > 0 from Customer c where c.email = :email";
        return entityManager.createQuery(jpql, Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }


    @Override
    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class,id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        Customer customer1 = entityManager.find(Customer.class,id);
        customer1.setName(customer.getName());
        customer1.setEmail(customer.getEmail());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setGender(customer.getGender());
        customer1.setSurname(customer.getSurname());
        customer1.setDateOfBirth(customer.getDateOfBirth());
    }

    @Override
    public void deleteCustomerById(Long id) {
            entityManager.remove(entityManager.find(Customer.class,id));
    }
    @Override
    public void assignCustomerToAgency(Long customerId, Long agencyId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        Agency agency = entityManager.find(Agency.class, agencyId);
        customer.getAgencyList().add(agency);
        agency.getCustomerList().add(customer);
    }
}

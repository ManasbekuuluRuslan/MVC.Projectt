package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Customer;
import peaksoft.repository.CustomerRepository;
import peaksoft.service.CustomerService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.saveCustomer(customer);
    }
    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
          customerRepository.updateCustomer(id, customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
      customerRepository.deleteCustomerById(id);
    }

    @Override
    public void assignCustomerToAgency(Long customerId, Long agencyId) {
        customerRepository.assignCustomerToAgency(customerId, agencyId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}

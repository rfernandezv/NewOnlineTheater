package theater.Customer.domain.repository;

import theater.Customer.domain.entity.Customer;
import java.util.List;

public interface CustomerRepository {
    List<Customer> getAll();
    Customer find(String name);
    void save(Customer customer);
}

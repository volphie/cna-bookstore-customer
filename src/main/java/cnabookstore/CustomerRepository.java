package cnabookstore;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>{

//    List<Customer> findByCustomerId(Long customerId);
}
package ma.formations.msa.customerservice.web;
import ma.formations.msa.customerservice.entities.Customer;
import ma.formations.msa.customerservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
@RestController public class CustomerRestController {
 private final CustomerRepository repository;
 public CustomerRestController(CustomerRepository repository){this.repository=repository;}
 @GetMapping("/customers") public List<Customer> listCustomers(){return repository.findAll();}
 @GetMapping("/customers/{id}") public Customer customerById(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found"));}
 @PostMapping("/customers") public Customer saveCustomer(@RequestBody Customer customer){return repository.save(customer);}
 @PutMapping("/customers/{id}") public Customer updateCustomer(@RequestBody Customer customer,@PathVariable Long id){customer.setId(id);return repository.save(customer);}
 @DeleteMapping("/customers/{id}") public void deleteCustomer(@PathVariable Long id){repository.deleteById(id);}
}

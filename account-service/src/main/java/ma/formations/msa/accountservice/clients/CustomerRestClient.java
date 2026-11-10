package ma.formations.msa.accountservice.clients;
import ma.formations.msa.accountservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@FeignClient(name="CUSTOMER-SERVICE") public interface CustomerRestClient {
 @GetMapping("/customers/{id}") Customer getCustomerById(@PathVariable("id") Long id);
 @GetMapping("/customers") List<Customer> getAllCustomers();
}

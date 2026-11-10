package ma.formations.msa.accountservice.controllers;
import ma.formations.msa.accountservice.clients.CustomerRestClient;
import ma.formations.msa.accountservice.entities.*;
import ma.formations.msa.accountservice.repository.AccountRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api") public class AccountRestController {
 private final AccountRepository repository; private final CustomerRestClient customers;
 public AccountRestController(AccountRepository repository,CustomerRestClient customers){this.repository=repository;this.customers=customers;}
 @GetMapping("/accounts") public List<BankAccount> list(){return repository.findAll();}
 @GetMapping("/accounts/{accountId}") @CircuitBreaker(name="customerService",fallbackMethod="fallbackAccount")
 public ResponseEntity<?> byId(@PathVariable String accountId){Optional<BankAccount> result=repository.findById(accountId);if(result.isEmpty())return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("errorMessage","account not found"));BankAccount account=result.get();account.setCustomer(customers.getCustomerById(account.getCustomerId()));return ResponseEntity.ok(account);}
 public ResponseEntity<?> fallbackAccount(String accountId,Throwable exception){Optional<BankAccount> result=repository.findById(accountId);if(result.isEmpty())return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("errorMessage","account not found"));BankAccount account=result.get();account.setCustomer(Customer.builder().id(account.getCustomerId()).firstName("Source not available").lastName("Source Not Available").build());return ResponseEntity.ok(account);}
}

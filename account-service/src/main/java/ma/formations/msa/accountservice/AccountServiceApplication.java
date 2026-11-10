package ma.formations.msa.accountservice;
import ma.formations.msa.accountservice.clients.CustomerRestClient;
import ma.formations.msa.accountservice.entities.*;
import ma.formations.msa.accountservice.repository.AccountRepository;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.util.*;
@SpringBootApplication @EnableFeignClients public class AccountServiceApplication {
 public static void main(String[] args){SpringApplication.run(AccountServiceApplication.class,args);}
 @Bean CommandLineRunner seed(AccountRepository repository,CustomerRestClient client){return args->{if(repository.count()>0)return;try{List<Customer> customers=client.getAllCustomers();for(Customer customer:customers){for(AccountType type:AccountType.values()){repository.save(BankAccount.builder().id(UUID.randomUUID().toString()).balance(Math.random()*80000).createdAt(LocalDate.now()).type(type).currency("MAD").customerId(customer.getId()).build());}}}catch(Exception e){System.err.println("Customer service unavailable at startup; no accounts seeded: "+e.getMessage());}};}
}

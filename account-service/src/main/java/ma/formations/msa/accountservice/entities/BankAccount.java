package ma.formations.msa.accountservice.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder public class BankAccount {
 @Id private String id; private Double balance; private LocalDate createdAt;
 @Enumerated(EnumType.STRING) private AccountType type; private String currency; private Long customerId;
 @Transient private Customer customer;
}

package ma.formations.msa.customerservice.web;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RefreshScope public class ConfigTestController {
 @Value("${customer.service.default.location:Casablanca}") private String location;
 @Value("${customer.service.default.language:Arabic}") private String language;
 @Value("${customer.service.default.currency:MAD}") private String currency;
 @GetMapping("/configTest") public Map<String,String> config(){return Map.of("defaultLocation",location,"defaultLanguage",language,"defaultCurrency",currency);}
}

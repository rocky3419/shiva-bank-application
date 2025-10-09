package bankingwebsite; // ✅ ADD THIS LINE

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/api/open-account")
    public ResponseEntity<String> openAccount(@RequestBody Account newAccount) {
        // You would add more validation and logic here
        
        accountRepository.save(newAccount);
        
        return ResponseEntity.ok("Account application submitted successfully!");
    }
}
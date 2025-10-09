package bankingwebsite; // ✅ ADD THIS LINE

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	// Add to your controller
	@Autowired
	private AccountRepository accountRepository;

	@PostMapping("/api/open-account")
	public ResponseEntity<String> openAccount(@RequestBody Account newAccount) {
	    // Add validation logic here if needed
	    
	    accountRepository.save(newAccount);
	    
	    return ResponseEntity.ok("Account application submitted successfully!");
	
    }
}
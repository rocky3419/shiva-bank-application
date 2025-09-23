package bankingwebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class should be in its own file: DashboardData.java
class DashboardData {
    public String username;
    public String accountNumber;
    public double balance;
}

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "https://bankingwebsite-p5ha.onrender.com")
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard-data")
    // ✅ Spring Security automatically provides the logged-in user's details
    public <UserDetails> ResponseEntity<DashboardData> getDashboardData(@AuthenticationPrincipal UserDetails userDetails) {
        
        // 1. Get the username of the currently logged-in user
        String currentUsername = ((User) userDetails).getUsername();

        // 2. Find that user in the database
        User user = userRepository.findByUsername(currentUsername)
                                  .orElseThrow(() -> new RuntimeException("User not found"));
                                  
        // 3. Create the response with that user's specific data
        DashboardData data = new DashboardData();
        data.username = user.getUsername();
        data.accountNumber = "...xxxx" + user.getPhone().substring(6); // Example
        data.balance = 125450.75; // Example

        return ResponseEntity.ok(data);
    }
}
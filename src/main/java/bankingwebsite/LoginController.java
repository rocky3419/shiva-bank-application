package bankingwebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://bankingwebsite-p5ha.onrender.com")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        // findByUsername returns an Optional<User>
        Optional<User> userOptional = userRepository.findByUsername(username.trim());

        // Check if the user exists
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // IMPORTANT: In a real app, you must compare hashed passwords
            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok("Login Successful!");
            }
        }
        
        // If user is not found or password doesn't match
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}
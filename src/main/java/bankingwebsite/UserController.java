package bankingwebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://bankingwebsite-p5ha.onrender.com")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User newUser) {
        if (userRepository.existsByEmail(newUser.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        // NOTE: HASH THE PASSWORD HERE IN A REAL APP
        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = Optional.of(userRepository.findByUsername(loginRequest.getUsername()));

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // IMPORTANT: Use password hashing in a real application
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok("Login Successful!");
            }
        }
        
        return ResponseEntity.status(401).body("Invalid credentials.");
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
package bankingwebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://bankingwebsite-p5ha.onrender.com")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        User user = userRepository.findByUsername(username.trim());

        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }
}

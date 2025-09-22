package bankingwebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(
    origins = "https://bankingwebsite-p5ha.onrender.com",  // ✅ Allow frontend domain
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // ✅ Check duplicate username
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("❌ Username already taken");
        }

        // ✅ Check duplicate email
        if (userRepository.existsByUsername(user.getEmail())) {
            return ResponseEntity.badRequest().body("❌ Email already registered");
        }

        // ✅ Save user
        userRepository.save(user);

        // ✅ Send welcome email
        emailService.sendRegistrationEmail(user);

        return ResponseEntity.ok("✅ Registration successful!");
    }
}

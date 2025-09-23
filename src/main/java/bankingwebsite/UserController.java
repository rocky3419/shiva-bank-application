package bankingwebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // ✅ ADD THIS ANNOTATION
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ... your other methods like login and register ...

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
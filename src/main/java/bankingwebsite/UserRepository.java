package bankingwebsite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Add this method
    User findByUsername(String username);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);

}
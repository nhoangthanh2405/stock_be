package net.javaguides.springboot.fileuploaddownload.repository;
import java.util.Optional;
import net.javaguides.springboot.fileuploaddownload.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  Optional<User> findByEmail(String email);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}

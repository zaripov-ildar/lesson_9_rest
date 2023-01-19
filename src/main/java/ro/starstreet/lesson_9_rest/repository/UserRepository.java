package ro.starstreet.lesson_9_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.starstreet.lesson_9_rest.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

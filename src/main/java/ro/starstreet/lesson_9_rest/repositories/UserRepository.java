package ro.starstreet.lesson_9_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.starstreet.lesson_9_rest.model.entities.Product;
import ro.starstreet.lesson_9_rest.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<Product> {
    User findByUsername(String username);
}

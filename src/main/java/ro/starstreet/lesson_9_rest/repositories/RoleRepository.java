package ro.starstreet.lesson_9_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.starstreet.lesson_9_rest.model.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

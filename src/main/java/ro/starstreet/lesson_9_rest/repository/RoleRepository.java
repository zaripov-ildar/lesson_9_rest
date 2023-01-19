package ro.starstreet.lesson_9_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.starstreet.lesson_9_rest.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}

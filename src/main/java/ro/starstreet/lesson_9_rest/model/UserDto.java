package ro.starstreet.lesson_9_rest.model;

import lombok.Data;
import ro.starstreet.lesson_9_rest.model.entities.Role;
import ro.starstreet.lesson_9_rest.model.entities.User;

import java.util.Collection;

@Data
public class UserDto {
    private long id;
    private String name;
    private Collection<Role> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getUsername();
        this.roles = user.getRoles();
    }

    @Override
    public String toString() {
        return id + "\t" + name + ", roles=" + roles;
    }
}

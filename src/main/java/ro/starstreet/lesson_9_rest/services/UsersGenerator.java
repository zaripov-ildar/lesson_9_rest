package ro.starstreet.lesson_9_rest.services;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ro.starstreet.lesson_9_rest.model.entities.User;
import ro.starstreet.lesson_9_rest.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsersGenerator {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void generateUsers() {
        Faker f = new Faker();
        // 42 in bcrypt
        String password = "$2a$12$umWFmDZ.25wkMM23sNUDdeGHWUo596Pfq6Jjyu9cfJIDh.SKcTO92";
        for (int i = 0; i < 10; i++) {
            String name = f.name().firstName();
            User user = new User();
            user.setUsername(name);
            user.setEmail(name + "@mail.com");
            user.setPassword(password);
            userRepository.save(user);
        }
        System.out.println(userRepository.findAll());
    }
}

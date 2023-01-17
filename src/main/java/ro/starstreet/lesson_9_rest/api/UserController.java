package ro.starstreet.lesson_9_rest.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.starstreet.lesson_9_rest.model.UserDto;
import ro.starstreet.lesson_9_rest.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String getUsersPage(Principal principal) {
        System.out.println(principal.getName());
        StringBuilder sb = new StringBuilder();
        userService.findAll().stream()
                .map(UserDto::new)
                .forEach(u -> sb.append("<p>")
                        .append(u)
                        .append("</p>"));
        return sb.toString();

    }
}

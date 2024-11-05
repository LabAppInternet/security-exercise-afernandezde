package cat.tecnocampus.securityjwt.api;

import cat.tecnocampus.securityjwt.domain.ERole;
import cat.tecnocampus.securityjwt.domain.Role;
import cat.tecnocampus.securityjwt.domain.UserLab;
import cat.tecnocampus.securityjwt.persistence.RoleRepository;
import cat.tecnocampus.securityjwt.persistence.UserLabRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class APIController {

    private final UserLabRepository userLabRepository;
    private final RoleRepository roleRepository;

    public APIController(UserLabRepository userLabRepository, RoleRepository roleRepository) {
        this.userLabRepository = userLabRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/helloAdmin")
    public String helloAdmin() {
        return "Hello Admin";
    }

    @GetMapping("/helloUser")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("/helloUserAdmin")
    public String helloUserAdmin() {
        return "Hello User or Admin";
    }

    @GetMapping("/helloMe")
    public String helloMe(Principal principal) {
        return "Hello " + principal.getName();
    }

    // getMapping /moderator returning the url
    @GetMapping("/moderator")
    public String moderator() {
        return "moderator";
    }

    @GetMapping("/moderator/aaa")
    public String moderatorAaa() {
        return "moderator/aaa";
    }

    @GetMapping("/moderator/bbb")
    public String moderatorBbb() {
        return "moderator/bbb";
    }

    @GetMapping("/moderator/ccc")
    public String moderatorCcc() {
        return "moderator/ccc";
    }

    @GetMapping("/moderator/aaa/admin")
    public String moderatorAaaAdmin() {
        return "moderator/aaa/admin";
    }

    @GetMapping("/moderator/bbb/admin")
    public String moderatorBbbAdmin() {
        return "moderator/bbb/admin";
    }

    @GetMapping("/moderator/ccc/admin")
    public String moderatorCccAdmin() {
        return "moderator/ccc/admin";
    }

    // TODO 2 add a PostMapping to create a new user with a single role. The role must be ADMIN or USER or MODERATOR
    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserLab createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam ERole role) {
        UserLab user = new UserLab(username, email, password);
        Role userRole = roleRepository.findByName(role.toString()).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.getRoles().add(userRole);
        return userLabRepository.save(user);
    }
}

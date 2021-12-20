package Cardoc.cardoc.controller;

import Cardoc.cardoc.exception.BadRequestException;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.service.UserService;
import lombok.RequiredArgsConstructor;
import Cardoc.cardoc.util.Token;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
@Transactional
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> newUser(@RequestBody UserForm userForm) {
        userService.createUser(userForm);
        return ResponseEntity.ok("CREATED");
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> logIn(@RequestBody UserForm userForm) {
        return ResponseEntity.ok(userService.loginUser(userForm));
    }
}

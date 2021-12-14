package Cardoc.cardoc.controller;

import Cardoc.cardoc.exception.BadRequestException;
import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.service.TrimService;
import Cardoc.cardoc.service.UserService;
import Cardoc.cardoc.util.Encryption;
import lombok.RequiredArgsConstructor;
import Cardoc.cardoc.util.Token;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
@Transactional
public class UserController {

    private final UserService userService;
    private final TrimService trimService;

    @PostMapping("/signup")
    public ResponseEntity<Object> newUser(@RequestBody UserForm userForm) {
        userService.createUser(userForm);

        return ResponseEntity.ok("CREATED");
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> logIn(@RequestBody UserForm userForm) {
        User findUser = userService.getUser(userForm.getAccount());

        if (findUser != null && BCrypt.checkpw(userForm.getPassword(), findUser.getPassword())) {
            return ResponseEntity.ok(Token.makeJwtToken(findUser));
        } else {
            throw new BadRequestException("INVALID_PASSWORD");
        }
    }

    @PostMapping("/trims")
    public ResponseEntity<Object> saveTrim(@RequestHeader("Authorization") String token, @RequestBody UserForm userForm) {
        User user = userService.getUser(Token.decodeJwtToken(token));
        Trim trim =  trimService.findTrim(userForm.getTrimId());
        if (userForm.getAccount().equals(user.getAccount())) {
            userService.addTrim(user.getId(), trim.getId());
        }
        return ResponseEntity.ok("SUCCESS");
    }
}

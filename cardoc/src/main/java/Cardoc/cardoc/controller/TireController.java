package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.*;
import Cardoc.cardoc.service.TireService;
import Cardoc.cardoc.service.TrimService;
import Cardoc.cardoc.service.UserService;
import Cardoc.cardoc.util.Token;
import Cardoc.cardoc.util.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("tires")
@Transactional
public class TireController {

    private final TireService tireService;
    private final TrimService trimService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<Object> createTire(@RequestBody TireForm tireForm) {
        tireService.createTire(tireForm);
        return ResponseEntity.ok("CREATED");
    }

    @GetMapping("")
    public ResponseEntity<Object> getTire(@RequestHeader("Authorization") String token) {
        User user = userService.getUser(Token.decodeJwtToken(token));
        List<Object> tires = tireService.getTireByUser(user);

        return ResponseEntity.ok(tires);
    }
}

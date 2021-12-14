package Cardoc.cardoc.controller;

import Cardoc.cardoc.exception.BadRequestException;
import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.service.CarService;
import Cardoc.cardoc.service.TrimService;
import Cardoc.cardoc.service.UserService;
import Cardoc.cardoc.util.Token;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;


@RequiredArgsConstructor
@RequestMapping("trims")
@Transactional
@RestController
public class TrimController {

    private final UserService userService;
    private final TrimService trimService;
    private final CarService carService;

    @PostMapping("")
    public ResponseEntity<Object> createTrim(@RequestBody TrimForm trimForm) {
        trimService.createTrim(trimForm);
        return ResponseEntity.ok("CREATED");
    }

    @GetMapping("/{trimId}")
    public ResponseEntity<Object> getTrim(@RequestHeader("Authorization") String token, @PathVariable(value = "trimId") Long trimId) {
        userService.getUser(Token.decodeJwtToken(token));
        Trim trim = trimService.findTrim(trimId);
        if (trim == null) {
            throw new BadRequestException("TRIM_DOES_NOT_EXIST");
        } else {
            return ResponseEntity.ok(trim);
        }

    }
}

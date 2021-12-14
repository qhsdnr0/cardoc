package Cardoc.cardoc.controller;

import Cardoc.cardoc.exception.BadRequestException;
import Cardoc.cardoc.models.Tire;
import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.models.UserTrimTire;
import Cardoc.cardoc.service.CarService;
import Cardoc.cardoc.service.TireService;
import Cardoc.cardoc.service.TrimService;
import Cardoc.cardoc.service.UserService;
import Cardoc.cardoc.util.Token;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("trims")
@Transactional
@RestController
public class TrimController {

    private final UserService userService;
    private final TrimService trimService;
    private final TireService tireService;

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

    @PutMapping("/{trimId}/{tireId}")
    public ResponseEntity<Object> addTires(
            @RequestHeader("Authorization") String token,
            @PathVariable(value = "trimId") Long trimId,
            @PathVariable(value = "tireId") Long tireId,
            @RequestBody TrimTireForm trimTireForm) {

        User user = userService.getUser(Token.decodeJwtToken(token));
        Trim trim = trimService.findTrim(trimId);
        Tire tire = tireService.getTire(tireId);

        String returnMessage = trimService.setUserTrimTire(user,tire,trim, trimTireForm);
        return ResponseEntity.ok(returnMessage);
    }
}

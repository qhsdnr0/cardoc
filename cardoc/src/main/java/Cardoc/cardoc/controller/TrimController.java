package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.service.CarService;
import Cardoc.cardoc.service.TrimService;
import Cardoc.cardoc.service.UserService;
import Cardoc.cardoc.util.Token;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;


@RequiredArgsConstructor
@RequestMapping("trim")
@Transactional
@RestController
public class TrimController {

    private final UserService userService;
    private final TrimService trimService;
    private final CarService carService;

    @PostMapping("")
    public void createTrim(@RequestBody TrimForm trimForm) {
        Trim trim = new Trim();
        trim.setCreatedAt(LocalDateTime.now());
        trim.setUpdatedAt(LocalDateTime.now());
        trim.setCar(carService.getCar(trimForm.getCarId()));
        trim.setName(trimForm.getName());
        trimService.createTrim(trim);
    }

    @GetMapping("/{trimId}")
    public HashMap<String, Object> getTrim(@RequestHeader("Authorization") String token, @PathVariable(value = "trimId") Long trimId) {
        HashMap<String, Object> result = new HashMap<>();
        User user = userService.getUser(Token.decodeJwtToken(token));
        Trim trim = trimService.findTrim(trimId);
        result.put("result", trim);
        return result;
    }
}

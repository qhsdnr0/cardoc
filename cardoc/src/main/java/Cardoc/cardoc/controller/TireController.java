package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.*;
import Cardoc.cardoc.service.TireService;
import Cardoc.cardoc.service.TrimService;
import Cardoc.cardoc.service.UserService;
import Cardoc.cardoc.util.Token;
import Cardoc.cardoc.util.Validation;
import lombok.RequiredArgsConstructor;
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
    private final Token accessToken;
    private final Validation validation;

    @PostMapping("")
    public void createTire(@RequestBody TireForm tireForm) {
        Tire tire = new Tire();
        TireInfo tireInfo = validation.makeTireInfo(tireForm.getTireInfo());

        tire.setTireInfo(tireInfo);
        tire.setCreatedAt(LocalDateTime.now());
        tire.setUpdatedAt(LocalDateTime.now());
        tire.setName(tireForm.getName());
        tire.setTrim(trimService.findTrim(tireForm.getTrimId()));
        tireService.createTire(tire);
    }

    @GetMapping("")
    public HashMap<String, Object> getTire(@RequestHeader("Authorization") String token) {
        HashMap<String, Object> result = new HashMap<>();

        User user = userService.getUser(accessToken.decodeJwtToken(token));
        List<Tire> tires = tireService.getTireByUser(user);

        result.put("result", tires);

        return result;
    }
}

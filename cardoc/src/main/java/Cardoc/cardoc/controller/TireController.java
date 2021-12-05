package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.*;
import Cardoc.cardoc.repository.TireRepository;
import Cardoc.cardoc.repository.TrimRepository;
import Cardoc.cardoc.repository.UserRepository;
import Cardoc.cardoc.service.TireService;
import Cardoc.cardoc.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("tires")
@Transactional
public class TireController {

    private final TireService tireService;
    private final UserRepository userRepository;
    private final Token accessToken;
    private final TireRepository tireRepository;
    private final TrimRepository trimRepository;

    @PostMapping("")
    public void createTire(@RequestBody TireForm tireForm) {
        Tire tire = new Tire();
        int width = Integer.parseInt(tireForm.getTireInfo().split("/")[0]);
        int flat = Integer.parseInt((tireForm.getTireInfo().split("/")[1].split("R")[0]));
        int wheelSize = Integer.parseInt((tireForm.getTireInfo().split("/")[1].split("R")[1]));

        TireInfo tireInfo = new TireInfo(flat, wheelSize, width);

        tire.setTireInfo(tireInfo);
        tire.setCreatedAt(LocalDateTime.now());
        tire.setUpdatedAt(LocalDateTime.now());
        tire.setName(tireForm.getName());
        tire.setTrim(trimRepository.findOne(tireForm.getTrimId()));
        tireService.createTire(tire);
    }

    @GetMapping("")
    public HashMap<String, Object> getTire(@RequestHeader("Authorization") String token) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            User user = userRepository.findOne(accessToken.decodeJwtToken(token));
            if (user == null) {
                throw new IllegalStateException("USER_DOES_NOT_EXIST");
            }
            List<UserTrim> userTrims = userRepository.findUserTrim(user);
            List<Trim> trims = trimRepository.findAll();
            List<Tire> tires = tireRepository.findByUser(user);

            result.put("result", tires);

        } catch (IllegalStateException e) {
            result.put("message", "USER_DOES_NOT_EXIST");
            e.getMessage();
        }
        return result;
    }
}

package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.Tire;
import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.models.UserTrim;
import Cardoc.cardoc.repository.CarRepository;
import Cardoc.cardoc.repository.TrimRepository;
import Cardoc.cardoc.repository.UserRepository;
import Cardoc.cardoc.service.TrimService;
import Cardoc.cardoc.token.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("trim")
@Transactional
@RestController
public class TrimController {

    private final UserRepository userRepository;
    private final TrimService trimService;
    private final TrimRepository trimRepository;
    private final CarRepository carRepository;
    private final Token accessToken;

    @PostMapping("")
    public void createTrim(@RequestBody TrimForm trimForm) {
        Trim trim = new Trim();
        trim.setCreatedAt(LocalDateTime.now());
        trim.setUpdatedAt(LocalDateTime.now());
        trim.setCar(carRepository.findOne(trimForm.getCarId()));
        trim.setName(trimForm.getName());
        trimService.createTrim(trim);
    }

    @GetMapping("/{trimId}")
    public HashMap<String, Object> getTrim(@RequestHeader("Authorization") String token, @PathVariable(value = "trimId") Long trimId) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            User user = userRepository.findOne(accessToken.decodeJwtToken(token));
            Trim trim = trimRepository.findOne(trimId);

            if (user == null) {
                throw new IllegalStateException("USER_DOES_NOT_EXIST");
            }
            result.put("result", trim);

        } catch (IllegalStateException e) {
            result.put("message", "USER_DOES_NOT_EXIST");
            e.getMessage();
        }

        return result;
    }
}

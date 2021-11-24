package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.Tire;
import Cardoc.cardoc.models.TireInfo;
import Cardoc.cardoc.repository.TrimRepository;
import Cardoc.cardoc.service.TireService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("tires")
@Transactional
public class TireController {

    private final TireService tireService;
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
}

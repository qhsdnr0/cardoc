package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.repository.CarRepository;
import Cardoc.cardoc.service.CarService;
import Cardoc.cardoc.service.TrimService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("trims")
@Transactional
public class TrimController {

    private final TrimService trimService;
    private final CarRepository carRepository;

    @PostMapping("")
    public void createTrim(@RequestBody TrimForm trimForm) {
        Trim trim = new Trim();
        trim.setCreatedAt(LocalDateTime.now());
        trim.setUpdatedAt(LocalDateTime.now());
        trim.setCar(carRepository.findOne(trimForm.getCarId()));
        trim.setName(trimForm.getName());
        trimService.createTrim(trim);
    }

}

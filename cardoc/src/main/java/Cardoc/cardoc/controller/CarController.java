package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.Car;
import Cardoc.cardoc.repository.CarRepository;
import Cardoc.cardoc.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Transactional
@RequestMapping("cars")
@RestController
@CrossOrigin
public class CarController {

    private final CarService carService;

    @PostMapping("")
    public void createCar(@RequestBody CarForm carForm) {
        Car car = new Car();
        car.setName(carForm.getName());
        carService.createCar(car);
    }
}

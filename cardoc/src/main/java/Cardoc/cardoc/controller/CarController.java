package Cardoc.cardoc.controller;

import Cardoc.cardoc.models.Car;
import Cardoc.cardoc.repository.CarRepository;
import Cardoc.cardoc.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Transactional
@RequestMapping("cars")
@RestController
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;

    @PostMapping("")
    public void createCar(@RequestBody CarForm carForm) {
        Car car = new Car();
        car.setName(carForm.getName());
        carService.createCar(car);
    }
}

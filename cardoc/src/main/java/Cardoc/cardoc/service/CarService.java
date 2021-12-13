package Cardoc.cardoc.service;

import Cardoc.cardoc.models.Car;
import Cardoc.cardoc.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {

    private final CarRepository carRepository;

    public void createCar(Car car) {
        car.setCreatedAt(LocalDateTime.now());
        car.setUpdatedAt(LocalDateTime.now());
        carRepository.save(car);
    }

    public Car getCar(Long id) {
        return carRepository.findOne(id);
    }
}

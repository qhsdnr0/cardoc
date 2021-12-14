package Cardoc.cardoc.service;

import Cardoc.cardoc.controller.TrimForm;
import Cardoc.cardoc.controller.TrimTireForm;
import Cardoc.cardoc.models.*;
import Cardoc.cardoc.repository.TireRepository;
import Cardoc.cardoc.repository.TrimRepository;
import Cardoc.cardoc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class TrimService {

    private final TrimRepository trimRepository;
    private final CarService carService;
    private final TireRepository tireRepository;

    public void createTrim(TrimForm trimForm) {
        Trim trim = new Trim();
        trim.setCreatedAt(LocalDateTime.now());
        trim.setUpdatedAt(LocalDateTime.now());
        trim.addCar(carService.getCar(trimForm.getCarId()));
        trim.setName(trimForm.getName());
        trimRepository.save(trim);
    }

    public Trim findTrim(Long id) {
        return trimRepository.findOne(id);
    }

    public String setUserTrimTire(User user, Tire tire, Trim trim, TrimTireForm trimTireForm) {

        UserTrimTire userTrimTire = trimRepository.findUserTrimTire(user, trim, tire);
        Tire newTire = trimTireForm.getTireId() == null ? tire : tireRepository.findOne(trimTireForm.getTireId());
        Trim newTrim = trimTireForm.getTrimId() == null ? trim : trimRepository.findOne(trimTireForm.getTrimId());
        if (userTrimTire == null) {
            userTrimTire = new UserTrimTire();
            userTrimTire.addUserTrimTire(newTrim, newTire, user);
            userTrimTire.setQuantity(trimTireForm.getQuantity());
            trimRepository.saveUserTrimTire(userTrimTire);
            return "CREATED";
        }

        userTrimTire.addUserTrimTire(newTrim, newTire, user);
        userTrimTire.setQuantity(trimTireForm.getQuantity());
        return "UPDATED";
    }

}

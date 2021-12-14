package Cardoc.cardoc.service;

import Cardoc.cardoc.controller.TrimForm;
import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.models.UserTrim;
import Cardoc.cardoc.repository.TrimRepository;
import Cardoc.cardoc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class TrimService {

    private final TrimRepository trimRepository;
    private final CarService carService;

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


}

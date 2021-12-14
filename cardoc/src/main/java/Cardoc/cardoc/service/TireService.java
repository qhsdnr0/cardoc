package Cardoc.cardoc.service;

import Cardoc.cardoc.controller.TireForm;
import Cardoc.cardoc.models.*;
import Cardoc.cardoc.repository.TireRepository;
import Cardoc.cardoc.repository.TrimRepository;
import Cardoc.cardoc.util.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TireService {

    private final TireRepository tireRepository;
    private final TrimService trimService;

    public void createTire(TireForm tireForm) {
        Tire tire = new Tire();
        TireInfo tireInfo = Validation.makeTireInfo(tireForm.getTireInfo());
        tire.setTireInfo(tireInfo);
        tire.setCreatedAt(LocalDateTime.now());
        tire.setUpdatedAt(LocalDateTime.now());
        tire.setName(tireForm.getName());

        tireRepository.save(tire);
    }

    public Tire getTire(Long id) {
        return tireRepository.findOne(id);
    }

    public List<Object> getTireByUser(User user) {
        return tireRepository.findByUser(user);
    }

}

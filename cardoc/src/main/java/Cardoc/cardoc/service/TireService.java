package Cardoc.cardoc.service;

import Cardoc.cardoc.models.Tire;
import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.repository.TireRepository;
import Cardoc.cardoc.repository.TrimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TireService {

    private final TrimRepository trimRepository;
    private final TireRepository tireRepository;

    public void createTire(Tire tire) {
        tireRepository.save(tire);
    }

}

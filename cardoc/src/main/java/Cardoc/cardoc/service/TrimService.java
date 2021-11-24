package Cardoc.cardoc.service;

import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.models.UserTrim;
import Cardoc.cardoc.repository.TrimRepository;
import Cardoc.cardoc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TrimService {

    private final TrimRepository trimRepository;
    private final UserRepository userRepository;

    public void createTrim(Trim trim) {
        trimRepository.save(trim);
    }

    public Trim findTrim(Long id) {
        return trimRepository.findOne(id);
    }


}

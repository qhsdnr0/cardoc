package Cardoc.cardoc.service;

import Cardoc.cardoc.models.User;
import Cardoc.cardoc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(User user) {
        validateUserAccount(user.getAccount());
        userRepository.saveUser(user);
    }

    public void deleteUser(User user) {
        userRepository.removeUser(user);
    }

    public void validateUserAccount(String account) {
        String pattern = "^[a-zA-Z][a-zA-Z0-9]{5,14}$";
        if (!account.matches(pattern)) {
             throw new IllegalStateException("INVALID_ACCOUNT");
        }
        if (!userRepository.findByAccount(account).isEmpty()) {
            throw  new IllegalStateException("DUPLICATED_USER");
        }
    }

    public void validateUserPassword(String password) {
        String pattern = ".*([!@#$%^&*].*)";
        if (!password.matches(pattern) || password.length() < 8 || password.length() > 18) {
            throw new IllegalStateException("INVALID_PASSWORD");
        }
    }
}

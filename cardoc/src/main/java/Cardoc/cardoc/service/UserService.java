package Cardoc.cardoc.service;

import Cardoc.cardoc.controller.UserForm;
import Cardoc.cardoc.exception.BadRequestException;
import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.repository.TrimRepository;
import Cardoc.cardoc.repository.UserRepository;
import Cardoc.cardoc.util.Encryption;
import Cardoc.cardoc.util.Token;
import Cardoc.cardoc.util.Validation;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserForm userForm) {
        User user = new User();
        if (userRepository.findByAccount(userForm.getAccount()) != null) {
            throw new BadRequestException("DUPLICATED_ACCOUNT");
        }

        Validation.validateUserAccount(userForm.getAccount());
        Validation.validateUserPassword(userForm.getPassword());

        String hashedPassword = Encryption.getHashedPassword(userForm.getPassword());
        user.setPassword(hashedPassword);

        user.setAccount(userForm.getAccount());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.saveUser(user);
    }

    public String loginUser(UserForm userForm) {
        User findUser = getUser(userForm.getAccount());
        String result = "";
        try {
            if (findUser != null && BCrypt.checkpw(userForm.getPassword(), findUser.getPassword())) {
                result = Token.makeJwtToken(findUser);
            } else {
                throw new AccessDeniedException("");
            }
        } catch (AccessDeniedException ex) {
            ex.printStackTrace();
            throw new BadRequestException("INVALID_PASSWORD");
        }
        return result;
    }

    public User getUser(Long id) {
        try {
            User user =  userRepository.findOne(id);
            if (user == null) {
                throw new IllegalStateException("USER_DOES_NOT_EXIST");
            }
            return user;
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("USER_DOES_NOT_EXIST");
        }

    }

    public User getUser(String account) {
        try {
            User user =  userRepository.findByAccount(account);
            if (user == null) {
                throw new IllegalStateException("USER_DOES_NOT_EXIST");
            }
            return user;
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("USER_DOES_NOT_EXIST");
        }
    }

    public void deleteUser(User user) {
        userRepository.removeUser(user);
    }
}

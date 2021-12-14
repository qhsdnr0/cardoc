package Cardoc.cardoc.service;

import Cardoc.cardoc.controller.UserForm;
import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.models.UserTrim;
import Cardoc.cardoc.repository.TrimRepository;
import Cardoc.cardoc.repository.UserRepository;
import Cardoc.cardoc.util.Encryption;
import Cardoc.cardoc.util.Token;
import Cardoc.cardoc.util.Validation;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TrimRepository trimRepository;

    public void createUser(UserForm userForm) {
        User user = new User();
        Validation.validateUserAccount(userForm.getAccount());
        Validation.validateUserPassword(userForm.getPassword());
        String hashedPassword = Encryption.getHashedPassword(userForm.getPassword());

        user.setAccount(userForm.getAccount());
        user.setPassword(hashedPassword);
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

    public void addTrim(Long userId, Long trimId) {
        User findUser = userRepository.findOne(userId);
        Trim findTrim = trimRepository.findOne(trimId);
        UserTrim userTrim = new UserTrim();
        userTrim.addUserTrim(findUser, findTrim);
        userRepository.saveUserTrim(userTrim);
    }

    public void createUserTrim(UserTrim userTrim) {
        userRepository.saveUserTrim(userTrim);
    }


}

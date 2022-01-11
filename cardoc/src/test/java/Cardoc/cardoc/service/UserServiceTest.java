package Cardoc.cardoc.service;

import Cardoc.cardoc.controller.UserForm;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.repository.UserRepository;
import Cardoc.cardoc.util.Token;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private EntityManager em;

    @BeforeEach
    void init() {
        UserForm userForm = new UserForm();
        userForm.setAccount("qhsdnr0");
        userForm.setPassword("asdf1234!");
        userService.createUser(userForm);
    }

//    @AfterEach
//    void tearDown() {
//        userRepository.removeUser();
//    }

    @Test
    void createUser() {
        //given
        UserForm userForm = new UserForm();
        userForm.setAccount("name11");
        userForm.setPassword("asdf1234!");
        assertNull(userRepository.findByAccount(userForm.getAccount()));

        userService.createUser(userForm);
        assertEquals(userRepository.findByAccount(userForm.getAccount()).getAccount(), userForm.getAccount());
    }

    @Test
    void loginUser() {
        UserForm userForm = new UserForm();
        userForm.setAccount("qhsdnr0");
        userForm.setPassword("asdf1234!");

        assertEquals(Token.makeJwtToken(userRepository.findByAccount(userForm.getAccount())), userService.loginUser(userForm));
    }
//
    @Test
    void getUser() {
        Long id = 1L;
        User user = userRepository.findOne(id);
        assertEquals(user, userService.getUser(id));
    }


    @Test
    void deleteUser() {
        Long id = 1L;
        User user = userRepository.findOne(id);
        userService.deleteUser(user);
        assertNull(userRepository.findOne(1L));

    }
}
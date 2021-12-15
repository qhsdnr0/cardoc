package Cardoc.cardoc.service;

import Cardoc.cardoc.controller.UserForm;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired EntityManager em;

//    @BeforeAll
//    static void before() {
//        MockitoAnnotations.openMocks(UserService.class);
//    }

    @Test
    public void createUser() {
        //given
        UserForm userForm = new UserForm();
        userForm.setAccount("name11");
        userForm.setPassword("asdf1234!");
        assertNull(userRepository.findByAccount(userForm.getAccount()));

        userService.createUser(userForm);
        em.flush();
        assertEquals(userRepository.findByAccount(userForm.getAccount()).getAccount(), userForm.getAccount());
    }

//    @Test
//    void loginUser() {
//    }
//
//    @Test
//    void getUser() {
//    }
//
//    @Test
//    void testGetUser() {
//    }
//
//    @Test
//    void deleteUser() {
//    }
}
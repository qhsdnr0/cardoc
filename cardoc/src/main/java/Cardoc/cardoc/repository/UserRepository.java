package Cardoc.cardoc.repository;

import Cardoc.cardoc.exception.BadRequestException;
import Cardoc.cardoc.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private final EntityManager em;

    public void saveUser(User user) {
        em.persist(user);
    }

    public void removeUser(User user) {
        em.remove(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public User findByAccount(String account) {
        List<User> userList = em.createQuery("select u from User u where u.account= :account", User.class)
                .setParameter("account", account)
                .getResultList();

        return userList.isEmpty() ? null : userList.get(0);
    }
}

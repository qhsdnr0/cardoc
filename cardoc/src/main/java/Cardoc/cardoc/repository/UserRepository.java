package Cardoc.cardoc.repository;

import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.models.UserTrim;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private final EntityManager em;

    public void saveUser(User user) {
        em.persist(user);
    }

    public void saveUserTrim(UserTrim userTrim) { em.persist(userTrim);}

    public void removeUser(User user) {
        em.remove(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findByAccount(String account) {
        return em.createQuery("select u from User u where u.account= :account", User.class)
                .setParameter("account", account)
                .getResultList();
    }
}

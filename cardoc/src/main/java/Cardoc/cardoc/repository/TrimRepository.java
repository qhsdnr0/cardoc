package Cardoc.cardoc.repository;

import Cardoc.cardoc.models.Tire;
import Cardoc.cardoc.models.Trim;
import Cardoc.cardoc.models.User;
import Cardoc.cardoc.models.UserTrimTire;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrimRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Trim trim) {
        em.persist(trim);
    }

    public Trim findOne(Long id) {
        return em.find(Trim.class, id);
    }

    public List<Trim> findAll() {
        return em.createQuery("select t from Trim t", Trim.class)
                .getResultList();
    }

    public void saveUserTrimTire(UserTrimTire userTrimTire) {
        em.persist(userTrimTire);
    }

    public UserTrimTire findUserTrimTire(User user, Trim trim, Tire tire) {
        List<UserTrimTire> result = em.createQuery("select u from UserTrimTire u where u.user= :user and u.trim= :trim and u.tire= :tire", UserTrimTire.class)
                .setParameter("user", user)
                .setParameter("trim", trim)
                .setParameter("tire", tire)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }
}

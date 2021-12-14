package Cardoc.cardoc.repository;

import Cardoc.cardoc.models.Tire;
import Cardoc.cardoc.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TireRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Tire tire) {
        em.persist(tire);
    }

    public Tire findOne(Long id) {
        return em.find(Tire.class, id);
    }

    public List<Tire> findByWidth(int width) {
        return em.createQuery("select t from Tire t where t.width = :width", Tire.class)
                .setParameter("width", width)
                .getResultList();
    }

    public List<Tire> findByFlat(int flat) {
        return em.createQuery("select t from Tire t where t.flat = :flat", Tire.class)
                .setParameter("flat", flat)
                .getResultList();
    }

    public List<Tire> findByWheelSize(int wheelSize) {
        return em.createQuery("select t from Tire t where t.wheelSize = :wheelSize", Tire.class)
                .setParameter("wheelSize", wheelSize)
                .getResultList();
    }

    public List<Object> findByUser(User user) {
        return em.createQuery("select t, sum(u.quantity) from Tire t join UserTrimTire u on t=u.tire " +
                        "and u.user= :user group by u.tire having t=u.tire order by t.id")
                .setParameter("user", user)
                .getResultList();

    }
}
package Cardoc.cardoc.repository;

import Cardoc.cardoc.models.Car;
import Cardoc.cardoc.models.Trim;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Car car) {
        em.persist(car);
    }

    public Car findOne(Long id) {
        return em.find(Car.class, id);
    }

    public Car findByName(String name) {
        return em.createQuery("select c from Car c where c.name = :name", Car.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Long> savedTrims(Car car) {
        List<Long> trimIdList = new ArrayList<>();
        List<Trim> trims = car.getTrims();
        for (Trim trim : trims) {
            trimIdList.add(trim.getId());
        }
        return trimIdList;
    }
}

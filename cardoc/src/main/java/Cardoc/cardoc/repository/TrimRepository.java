package Cardoc.cardoc.repository;

import Cardoc.cardoc.models.Trim;
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
}

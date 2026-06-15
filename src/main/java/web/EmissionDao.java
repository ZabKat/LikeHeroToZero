package web;

import model.Emission;
import jakarta.persistence.*;
import java.util.List;

public class EmissionDao {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ArtikelPU");

    private EntityManager getEm() {
        return emf.createEntityManager();
    }

    public List<Emission> findAll() {
        EntityManager em = getEm();
        try {
            return em.createQuery("SELECT e FROM Emission e", Emission.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<String> findAllCountries() {
        EntityManager em = getEm();
        try {
            return em.createQuery("SELECT DISTINCT e.countryName FROM Emission e", String.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Emission findLatestByCountry(String countryName) {
        EntityManager em = getEm();
        try {
            List<Emission> results = em.createQuery(
                    "SELECT e FROM Emission e WHERE e.countryName = :name ORDER BY e.year DESC",
                    Emission.class)
                .setParameter("name", countryName)
                .setMaxResults(1)
                .getResultList();

            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    public void create(Emission e) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Emission e) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Emission e) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        em.remove(em.merge(e));
        em.getTransaction().commit();
        em.close();
    }
}
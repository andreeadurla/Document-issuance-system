package repository;

import entity.Admin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AdminRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public Admin findByEmail(String email) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT a FROM Admin a WHERE a.email = :admin_email");
        query.setParameter("admin_email", email);

        Admin admin = null;
        try {
            admin = (Admin) query.getResultList().get(0);
        }
        catch(Exception e) {

        }
        finally {
            em.close();
            return admin;
        }
    }
}

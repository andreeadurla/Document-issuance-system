package repository;

import entity.House;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class HouseRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public boolean findHouse(House house) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT h FROM House h " +
                                        "WHERE h.county = :county AND h.city = :city AND "+
                                            "h.address = :address AND h.deleted = false");

        query.setParameter("county", house.getCounty());
        query.setParameter("city", house.getCity());
        query.setParameter("address", house.getAddress());

        boolean found = true;
        if(query.getResultList().isEmpty())
            found = false;

        em.close();
        return found;
    }

    public List<House> findAllHouses(String userID) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT h FROM House h WHERE h.user.id = :user_id AND h.deleted = false");
        query.setParameter("user_id", userID);

        List<House> houses = query.getResultList();
        em.close();
        return houses;
    }

    private String existsHouseAndIsDeleted(House house) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT h.id FROM House h WHERE h.county = :county AND h.city = :city AND " +
                                    "h.address = :address AND h.user.id = :user_id AND h.deleted = true");

        query.setParameter("county", house.getCounty());
        query.setParameter("city", house.getCity());
        query.setParameter("address", house.getAddress());
        query.setParameter("user_id", house.getUserId());

        String id = null;
        try {
            id = (String) query.getResultList().get(0);
        }
        catch(Exception e) {

        }
        finally {
            em.close();
            return id;
        }
    }

    public void insertNewHouse(House house) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        String id = existsHouseAndIsDeleted(house);

        if(id != null) {

            Query query = em.createQuery("Update House h SET h.deleted = false " +
                                            "WHERE h.id = :house_id");

            query.setParameter("house_id", id);
            query.executeUpdate();
        }
        else
            em.persist(house);

        em.getTransaction().commit();
        em.close();
    }

    public void deleteHouse(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("Update House h SET h.deleted = :deleted_status " +
                                        "WHERE h.id = :house_id");
        query.setParameter("deleted_status", true);
        query.setParameter("house_id", id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

}

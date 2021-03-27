package repository;

import entity.*;
import utils.ApplicationUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class RequestRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewRequest(Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(request);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteByUpdatingColumn(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("Update Request r SET r.deleted = true " +
                                    "WHERE r.id = :request_id");

        query.setParameter("request_id", id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public int updateRequestStatus(String id, String status) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("Update Request r SET r.status = :request_status " +
                                        "WHERE r.id = :request_id");
        query.setParameter("request_status", status);
        query.setParameter("request_id", id);
        int updatedRows = query.executeUpdate();
        em.getTransaction().commit();
        em.close();

        return updatedRows;
    }

    public void updateRequest(Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("Update Request r SET r.house.id = :house_id,  " +
                                    "r.document.id = :document_id,  r.purpose = :purpose " +
                                            "WHERE r.id = :request_id");

        query.setParameter("house_id", request.getHouse_id());
        query.setParameter("document_id", request.getDocument_id());
        query.setParameter("purpose", request.getPurpose());
        query.setParameter("request_id", request.getId());

        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public List<Request> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT r FROM Request r WHERE r.deleted = false");
        List<Request> requests = query.getResultList();
        em.close();
        return requests;
    }

    public List<Request> findByUserID(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT r FROM Request r WHERE r.user.id = :user_id AND r.deleted = false");
        query.setParameter("user_id", id);
        List<Request> requests = query.getResultList();
        em.close();

        return requests;
    }

    public List<Request> findByStatus(String status) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT r FROM Request r WHERE r.status = :request_status AND r.deleted = false");
        query.setParameter("request_status", status);
        List<Request> requests = query.getResultList();
        em.close();

        return requests;
    }

    public Request findById(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT r FROM Request r WHERE r.id = :request_id AND r.deleted = false");
        query.setParameter("request_id", id);

        Request request = null;
        try {
            request = (Request) query.getResultList().get(0);
        }
        catch(Exception e) {

        }
        finally {
            em.close();
            return request;
        }
    }

    //returns number of rows found that have same user_id, document_id and house_id.
    public long getNoRequests(Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT COUNT(*) FROM Request r " +
                                        "WHERE r.user.id = :user_id AND r.document.id= :document_id AND r.house.id = :house_id AND " +
                                              "r.date BETWEEN :start_date AND :end_date AND (r.status <> :status OR r.deleted = false)");

        query.setParameter("user_id", request.getUser_id());
        query.setParameter("document_id", request.getDocument_id());
        query.setParameter("house_id", request.getHouse_id());
        query.setParameter("status", Status_level.IN_PROCESSING.toString());

        Date date1 = ApplicationUtils.getFirstDateOfCurrentYear();
        Date date2 = ApplicationUtils.getLastDateOfCurrentYear();

        query.setParameter("start_date", date1);
        query.setParameter("end_date", date2);

        long no = (Long) query.getResultList().get(0);
        em.close();

        return no;
    }
}

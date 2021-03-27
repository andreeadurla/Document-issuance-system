package repository;

import entity.Document;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DocumentRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public boolean findDocument(Document document) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT d FROM Document d WHERE d.name = :document_name AND d.deleted = false");
        query.setParameter("document_name", document.getName());

        boolean found = true;
        if(query.getResultList().isEmpty())
            found = false;

        em.close();
        return found;
    }

    private boolean isDocumentDeleted(Document document) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT d FROM Document d WHERE d.name = :document_name AND d.deleted = true");

        query.setParameter("document_name", document.getName());

        boolean found = true;
        if(query.getResultList().isEmpty())
            found = false;

        em.close();
        return found;
    }

    public void insertNewDocumentType(Document document) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        boolean isDeleted = isDocumentDeleted(document);
        System.out.println(isDeleted);

        if(isDeleted) {

            Query query = em.createQuery("Update Document d SET d.deleted = false " +
                                        "WHERE d.name = :document_name");

            query.setParameter("document_name", document.getName());
            query.executeUpdate();
        }
        else
            em.persist(document);

        em.getTransaction().commit();
        em.close();
    }

    public List<Document> findAllDocumentTypes() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT d FROM Document d WHERE d.deleted = false");

        List<Document> documents  = query.getResultList();
        em.close();
        return documents;
    }

    public void deleteDocumentType(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("Update Document d SET d.deleted = :deleted_status " +
                                                "WHERE d.id = :document_id");
        query.setParameter("deleted_status", true);
        query.setParameter("document_id", id);
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
    }
}

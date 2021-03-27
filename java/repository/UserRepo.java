package repository;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserRepo {
	
	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

	public void insertNewUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public List<User> findAllUsers() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("SELECT u FROM User u");
		List<User> users = query.getResultList();
		em.close();
		return users;
	}

	public User findByEmailOrCNP(String email, String cnp) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :user_email OR u.cnp = :user_cnp");
		query.setParameter("user_email", email);
		query.setParameter("user_cnp", cnp);

		User user = null;
		try {
			user = (User) query.getResultList().get(0);
		}
		catch(Exception e) {

		}
		finally {
			em.close();
			return user;
		}

	}

	public User findByEmail(String email) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :user_email");
		query.setParameter("user_email", email);

		User user = null;
		try {
			user = (User) query.getResultList().get(0);
		}
		catch(Exception e) {

		}
		finally {
			em.close();
			return user;
		}
	}
}

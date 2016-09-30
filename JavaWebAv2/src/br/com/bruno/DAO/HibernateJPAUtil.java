package br.com.bruno.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateJPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db_user");
	
	public EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	
	public void close(EntityManager em){
		em.close();
	}
	
}

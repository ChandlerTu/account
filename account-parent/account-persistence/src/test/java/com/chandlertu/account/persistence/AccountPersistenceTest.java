package com.chandlertu.account.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.chandlertu.account.Account;

import junit.framework.TestCase;

public class AccountPersistenceTest extends TestCase {

	private EntityManagerFactory entityManagerFactory;

	@Override
	protected void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("com.chandlertu.account.jpa");
	}

	@Override
	protected void tearDown() throws Exception {
		entityManagerFactory.close();
	}

	@Test
	public void testBasicUsage() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Account account = new Account();
		account.setEmail("chandlertu@chandlertu.com");
		account.setPassword("tU*****");
		entityManager.persist(account);
		entityManager.getTransaction().commit();
		entityManager.close();

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Account> result = entityManager.createQuery("from Account", Account.class).getResultList();
		for (Account event : result) {
			System.out.println("Acount: " + event.getId() + " " + event.getEmail());
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}

package com.dev.vkbot.database.personmanagment.dao;

import com.dev.vkbot.database.personmanagment.model.Person;
import com.dev.vkbot.database.personmanagment.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonDao {
		/**
		 * User to save
		 *
		 * @param person user to save
		 */
		
		public void PersistPerson(Person person) {
				Transaction transaction = null;
				try {
						Session session = HibernateUtil.GetSessionFactory().openSession();
						transaction = session.beginTransaction();
						session.persist(person);
						transaction.commit();
				} catch (Exception e) {
						if (transaction != null) {
								transaction.rollback();
						}
						e.printStackTrace();
				}
		}
		
		public void MergePerson(Person person) {
				Transaction transaction = null;
				try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
						transaction = session.beginTransaction();
						session.merge(person);
						transaction.commit();
				} catch (Exception e) {
						if (transaction != null) {
								transaction.rollback();
						}
						e.printStackTrace();
				}
		}
		
		public Person GetPerson(int personId) {
				Transaction transaction = null;
				Person person = null;
				try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
						transaction = session.beginTransaction();
						person = session.get(Person.class, personId);
						transaction.commit();
				} catch (Exception e) {
						if (transaction != null) {
								transaction.rollback();
						}
						e.printStackTrace();
				}
				return person;
		}
		
		@SuppressWarnings("unchecked")
		public List<Person> getAllPersons() {
				Transaction transaction = null;
				List<Person> persons = null;
				
				try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
						transaction = session.beginTransaction();
						
						persons = session.createQuery("from User").getResultList();
						transaction.commit();
				} catch (Exception e) {
						if (transaction != null) {
								transaction.rollback();
						}
						e.printStackTrace();
				}
				return persons;
		}
}

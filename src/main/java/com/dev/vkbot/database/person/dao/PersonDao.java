package com.dev.vkbot.database.person.dao;

import com.dev.vkbot.database.person.model.Person;
import com.dev.vkbot.database.utils.HibernateUtil;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;

public class PersonDao {
		HibernateCriteriaBuilder criteriaBuilder;
		
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
						e.printStackTrace();
				}
		}
		
		public Person GetPerson(long personId) {
				Transaction transaction = null;
				Person person = null;
				try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
						transaction = session.beginTransaction();
						person = session.get(Person.class, personId);
						transaction.commit();
				} catch (Exception e) {
						e.printStackTrace();
				}
				return person;
		}
		
		public Person GetPerson(long vkId, @MaybeNull boolean blankparam) {
				Transaction transaction = null;
				Person person = null;
				try (Session session = HibernateUtil.GetSessionFactory().getCurrentSession()) {
						transaction = session.beginTransaction();
						this.criteriaBuilder = session.getCriteriaBuilder();
						JpaCriteriaQuery<Person> personCriteriaQuery = this.GetPersonByVkIdCriteriaQuery(criteriaBuilder, vkId);
						Query<Person> personQuery = session.createQuery(personCriteriaQuery);
						person = personQuery.getResultList().getFirst();
						transaction.commit();
				} catch (Exception e) {
						e.printStackTrace();
				}
				return person;
		}
		
		public List<Person> getAllPersons() {
				Transaction transaction = null;
				List<Person> persons = null;
				
				try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
						transaction = session.beginTransaction();
						this.criteriaBuilder = session.getCriteriaBuilder();
						JpaCriteriaQuery<Person> query = this.GetAllPersonsCriteriaQuery(this.criteriaBuilder);
						Query<Person> allQuery = session.createQuery(query);
						persons = allQuery.getResultList();
						transaction.commit();
				} catch (Exception e) {
						
						e.printStackTrace();
				}
				return persons;
		}
		
		
		private JpaCriteriaQuery<Person> GetAllPersonsCriteriaQuery(HibernateCriteriaBuilder criteriaBuilder) {
				JpaCriteriaQuery<Person> cr = criteriaBuilder.createQuery(Person.class);
				JpaRoot<Person> root = cr.from(Person.class);
				JpaCriteriaQuery<Person> all = cr.select(root);
				
				return all;
		}
		
		private JpaCriteriaQuery<Person> GetPersonByVkIdCriteriaQuery(HibernateCriteriaBuilder criteriaBuilder, long vkId) {
				JpaCriteriaQuery<Person> cr = criteriaBuilder.createQuery(Person.class);
				JpaRoot<Person> root = cr.from(Person.class);
				String pattern = String.format("%%%d%%", vkId);
				JpaCriteriaQuery<Person> item = cr.select(root).where(root.get("vkId").in(vkId));
				
				return item;
		}
}

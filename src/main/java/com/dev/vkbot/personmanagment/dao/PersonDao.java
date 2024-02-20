package com.dev.vkbot.personmanagment.dao;

import com.dev.vkbot.personmanagment.model.Person;
import com.dev.vkbot.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonDao {
		public void savePerson(Person person) {
				Transaction transaction = null;
				Session session = HibernateUtil.GetSessionFactory().openSession();
				transaction = session.beginTransaction();
				session.persist(person);
				transaction.commit();
				session.close();
		}
}

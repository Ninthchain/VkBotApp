package com.dev.bot.database.dao;

import com.dev.bot.database.model.Person;
import com.dev.bot.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;



import java.util.List;


public class PersonDao implements Dao<Person> {
    HibernateCriteriaBuilder criteriaBuilder;


    @Override
    public void Persist(Person entityObject) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.GetSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(entityObject);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void Merge(Person entityObject) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entityObject);
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public <IdType extends Number> Person Get(IdType id) {

        Person person = null;
        try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            person = session.get(Person.class, id);
            transaction.commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return person;
    }

    @Override
    public <ColumnValueType> List<Person> getByColumnValue(String columnName, ColumnValueType value) {

        List<Person> values = null;

        try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            this.criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<Person> cr = criteriaBuilder.createQuery(Person.class);
            JpaRoot<Person> root = cr.from(Person.class);
            JpaCriteriaQuery<Person> items = cr.select(root).where(root.get(columnName).in(value));
            Query<Person> valuesQuery = session.createQuery(items);
            values = valuesQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return values;
    }

    @Override
    public List<Person> getAll() {
        List<Person> values = null;

        try (Session session = HibernateUtil.GetSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();

            this.criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<Person> cr = criteriaBuilder.createQuery(Person.class);
            JpaRoot<Person> root = cr.from(Person.class);
            JpaCriteriaQuery<Person> selectQuery = cr.select(root);
            Query<Person> allQuery = session.createQuery(selectQuery);
            values = allQuery.getResultList();

            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return values;
    }


}

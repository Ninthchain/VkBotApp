package com.dev.bot.database.dao;

import com.dev.bot.database.model.Person;
import com.dev.bot.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;



import java.util.List;


public class PersonDao implements Dao<Person> {
    HibernateCriteriaBuilder criteriaBuilder;
    

    @Override
    public void persist(Person entityObject) {
    
    }

    @Override
    public void merge(Person entityObject) {

    }

    @Override
    public <IdType> Person getEntityById(IdType id) {
    
    }

    @Override
    public <ColumnValueType> List<Person> getEntitiesByColumnValue(String columnName, ColumnValueType value) {

        List<Person> values = null;

        try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            
            Query<Person> personQuery = session.createQuery(String.format("from Person where %s = %s", columnName, value.toString()), Person.class);
            
            values = personQuery.getResultList();
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
            
            Query<Person> allQuery = session.createQuery("from Person", Person.class);
            values = allQuery.getResultList();

            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return values;
    }


}

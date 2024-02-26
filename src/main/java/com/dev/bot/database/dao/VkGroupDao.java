package com.dev.bot.database.dao;

import com.dev.bot.database.model.VkGroup;
import com.dev.bot.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;


import java.util.List;

public class VkGroupDao implements Dao<VkGroup> {
    HibernateCriteriaBuilder criteriaBuilder;

    @Override
    public void persist(VkGroup entityObject) {
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
    
    /**
     * @param entityObject the id must be not null
     */
    @Override
    public void merge(VkGroup entityObject) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.flush();
            session.clear();
            session.detach(this.getEntityById(entityObject.getId()));
            session.merge(entityObject);
            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public <IdType extends Number> VkGroup getEntityById(IdType id) {
        VkGroup value = null;
        try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            value = session.get(VkGroup.class, id);
            transaction.commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return value;
    }

    @Override
    public <ColumnValueType> List<VkGroup> getEntitiesByColumnValue(String columnName, ColumnValueType value) {

        List<VkGroup> values = null;

        try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<VkGroup> groupQuery = session.createQuery(String.format("from VkGroup where %s like %s", columnName, value.toString()), VkGroup.class);
            values = groupQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return values;
    }

    @Override
    public List<VkGroup> getAll() {

        List<VkGroup> values = null;

        try (Session session = HibernateUtil.GetSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            
            Query<VkGroup> allQuery = session.createQuery("from vkgroup", VkGroup.class);
            values = allQuery.getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }
}

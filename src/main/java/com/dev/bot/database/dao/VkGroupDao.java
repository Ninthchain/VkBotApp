package com.dev.bot.database.dao;

import com.dev.bot.database.model.VkGroup;
import com.dev.bot.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;

public class VkGroupDao implements Dao<VkGroup> {
    HibernateCriteriaBuilder criteriaBuilder;

    @Override
    public void Persist(VkGroup entityObject) {
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
    public void Merge(VkGroup entityObject) {
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
    public <IdType extends Number> VkGroup Get(IdType id) {
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
    public <ColumnValueType> List<VkGroup> getByColumnValue(String columnName, ColumnValueType value) {

        List<VkGroup> values = null;

        try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            this.criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<VkGroup> cr = criteriaBuilder.createQuery(VkGroup.class);
            JpaRoot<VkGroup> root = cr.from(VkGroup.class);
            JpaCriteriaQuery<VkGroup> items = cr.select(root).where(root.get(columnName).in(value));
            Query<VkGroup> valuesQuery = session.createQuery(items);
            values = valuesQuery.getResultList();
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

            this.criteriaBuilder = session.getCriteriaBuilder();
            JpaCriteriaQuery<VkGroup> cr = criteriaBuilder.createQuery(VkGroup.class);
            JpaRoot<VkGroup> root = cr.from(VkGroup.class);
            JpaCriteriaQuery<VkGroup> selectQuery = cr.select(root);
            Query<VkGroup> allQuery = session.createQuery(selectQuery);
            values = allQuery.getResultList();

            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return values;
    }
}

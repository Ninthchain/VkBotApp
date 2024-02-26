package com.dev.bot.database.dao;


import com.dev.bot.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class Dao<EntityType extends Model<Long>> {
    void persist(EntityType entityObject) {
		    Transaction transaction = null;
		    try (Session session = HibernateUtil.GetSessionFactory().openSession()){
				    transaction = session.beginTransaction();
				    session.persist(entityObject);
				    transaction.commit();
		    } catch (Exception e) {
				    
				    e.printStackTrace();
		    }
    }

    void merge(EntityType entityObject) {
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

    <IdType extends Number> EntityType getEntityById(IdType id) {
		    EntityType entity = null;
		    try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
				    Transaction transaction = session.beginTransaction();
				    entity = session.get((Class<EntityType>) entity.getClass(), id);
				    transaction.commit();
				    
		    } catch (Exception e) {
				    
				    e.printStackTrace();
		    }
		    return entity;
    }

    <ColumnValueType> List<EntityType> getEntitiesByColumnValue(String columnName, ColumnValueType value) {
		  
    }

    List<EntityType> getAll() {
		  
    }
}

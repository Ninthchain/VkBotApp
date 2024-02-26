package com.dev.bot.database.dao;

import com.dev.bot.database.model.SellPoint;
import com.dev.bot.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SellPointDao implements Dao<SellPoint> {
		@Override
		public void persist(SellPoint entityObject) {
				try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
						Transaction transaction = session.beginTransaction();
						session.persist(entityObject);
						session.flush();
						transaction.commit();
				}
		}
		
		@Override
		public void merge(SellPoint entityObject) {
		
		}
		
		@Override
		public <IdType extends Number> SellPoint getEntityById(IdType id) {
				return null;
		}
		
		@Override
		public <ColumnValueType> List<SellPoint> getEntitiesByColumnValue(String columnName, ColumnValueType value) {
				return null;
		}
		
		@Override
		public List<SellPoint> getAll() {
				return null;
		}
}

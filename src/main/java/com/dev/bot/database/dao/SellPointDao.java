package com.dev.bot.database.dao;

import com.dev.bot.controllers.SellPointServlet;
import com.dev.bot.database.model.Person;
import com.dev.bot.database.model.SellPoint;
import com.dev.bot.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SellPointDao implements Dao<SellPoint> {
		@Override
		public void persist(SellPoint entityObject) {
				try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
						Transaction transaction = session.beginTransaction();
						session.persist(entityObject);
						session.flush();
						transaction.commit();
				} catch (Exception e) {
						
						e.printStackTrace();
				}
		}
		
		@Override
		public void merge(SellPoint entityObject) {
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
		public <IdType extends Number> SellPoint getEntityById(IdType id) {
				SellPoint value = null;
				try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
						Transaction transaction = session.beginTransaction();
						value = session.get(SellPoint.class, id);
						transaction.commit();
						
				} catch (Exception e) {
						
						e.printStackTrace();
				}
				return value;
		}
		
		@Override
		public <ColumnValueType> List<SellPoint> getEntitiesByColumnValue(String columnName, ColumnValueType value) {
				List<SellPoint> values = null;
				
				try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
						Transaction transaction = session.beginTransaction();
						
						Query<SellPoint> personQuery = session.createQuery(String.format("from Person where %s = %s", columnName, value.toString()), SellPoint.class);
						
						values = personQuery.getResultList();
						transaction.commit();
				} catch (Exception e) {
						
						e.printStackTrace();
				}
				return values;
		}
		
		@Override
		public List<SellPoint> getAll() {
				List<SellPoint> values = null;
				
				try (Session session = HibernateUtil.GetSessionFactory().openSession()) {
						
						Transaction transaction = session.beginTransaction();
						
						Query<SellPoint> allQuery = session.createQuery("from Person", SellPoint.class);
						values = allQuery.getResultList();
						
						transaction.commit();
				} catch (Exception e) {
						
						e.printStackTrace();
				}
				return values;
		}
}

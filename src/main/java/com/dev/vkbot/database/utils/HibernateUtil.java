package com.dev.vkbot.database.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
		
		public static SessionFactory GetSessionFactory() {
				
				return new Configuration().configure().buildSessionFactory();
		}
}

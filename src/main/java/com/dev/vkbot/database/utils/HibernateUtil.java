package com.dev.vkbot.database.personmanagment.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
		
		public static SessionFactory GetSessionFactory() {
				
				return new Configuration().buildSessionFactory();
		}
}

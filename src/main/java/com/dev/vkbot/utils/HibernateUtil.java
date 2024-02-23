package com.dev.vkbot.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Ninthchain
 *
 */
public class HibernateUtil {
		
		public static SessionFactory GetSessionFactory() {
				
				return new Configuration().configure().buildSessionFactory();
		}
}

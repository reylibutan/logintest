package com.libutan.rey.logintest;

import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.libutan.rey.logintest.entity.Login;

@SpringBootApplication
public class LogintestDataLoader {

	private static Random rand = new Random();
	private static String TABLE_NAME = "Login";
	private static long MIN_TIME = 1472659200000L; // September 01, 2016 00:00:00 (GMT + 8)
	private static long MAX_TIME = 1475251199000L; // September 31, 2016 23:59:59 (GMT + 8)
	private static int NO_OF_RECORDS = 100_000;
	private static String[] NAMES = new String[] {"Feng Wei", "Wang Jinrei", "Raven", "Eleonore Kliesen", "Kuma", "Panda", "Lee Chaolan"};
	private static String[] ATTRIBUTES = new String[] {null, "red", "orange", "yellow", "green", "blue", "indigo", "violet"};

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LogintestDataLoader.class, args);

		HibernateEntityManagerFactory emFactory = (HibernateEntityManagerFactory) ctx.getBean("entityManagerFactory");
		StatelessSession session = null;
		Transaction transaction = null;
		
		try {
			session = emFactory.getSessionFactory().openStatelessSession();
			transaction = session.beginTransaction();

			// delete table (not truncate so as to be rollback-able)
			session.createQuery("DELETE FROM " + TABLE_NAME).executeUpdate();

			for (int i = 0 ; i < NO_OF_RECORDS ; i++) {
				Login login = new Login();
				login.setLoginTime(new Date(MIN_TIME + (long)(rand.nextDouble() * (MAX_TIME - MIN_TIME))));
				login.setUser(NAMES[rand.nextInt(NAMES.length)]);
				login.setAttribute1(ATTRIBUTES[rand.nextInt(ATTRIBUTES.length)]);
				login.setAttribute2(ATTRIBUTES[rand.nextInt(ATTRIBUTES.length)]);
				login.setAttribute3(ATTRIBUTES[rand.nextInt(ATTRIBUTES.length)]);
				login.setAttribute4(ATTRIBUTES[rand.nextInt(ATTRIBUTES.length)]);

				session.insert(login);
			}

			transaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		SpringApplication.exit(ctx);
	}
}
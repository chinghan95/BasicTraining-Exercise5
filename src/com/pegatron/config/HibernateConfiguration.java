/**
 * This class represents hibernate configuration.
 * 
 * @Configuration indicates that this class contains one or more bean methods
 * annotated with @Bean producing beans manageable by spring container.
 * 
 * @ComponentScan looks for spring managed beans/classes.
 * 
 * @EnableTransactionManagement enables Spring's annotation-driven 
 * transaction management capability.
 */

package com.pegatron.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan("com.pegatron.config")
@EnableTransactionManagement
public class HibernateConfiguration<T> {

	@Bean(name = "dataSource")
//	@Scope("singleton")
	public DataSource getDataSource() throws PropertyVetoException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver"); // loads the jdbc driver
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/bookstore?serverTimezone=UTC");
		cpds.setUser("root");
		cpds.setPassword("1234");
		cpds.setMinPoolSize(5);

		return cpds;
	}

	@Bean(name = "sessionFactory")
//	@Scope("singleton")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		// Scan packages for annotated classes
		sessionBuilder.scanPackages("com.pegatron");
		sessionBuilder.addProperties(getHibernateProperties());
		return sessionBuilder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		properties.put("hibernate.show_sql", "true");
		return properties;
	}

	/*
	 * By configuring a transaction manager, code in the Dao class doesn't have to
	 * take care of transaction management explicitly. Instead, the @Transactional
	 * annotation is used to tell Spring automatically inserts transaction
	 * management code into the bytecode.
	 */
//	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}

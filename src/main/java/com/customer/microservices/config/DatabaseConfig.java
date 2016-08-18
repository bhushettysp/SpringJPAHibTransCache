package com.customer.microservices.config;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.cache.CacheBuilder;



@Configuration
@EnableTransactionManagement
@EnableCaching
public class DatabaseConfig {

	@Value("${db.driver}")
	private String DB_DRIVER;

	@Value("${db.password}")
	private String DB_PASSWORD;

	@Value("${db.url}")
	private String DB_URL;

	@Value("${db.username}")
	private String DB_USERNAME;

	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HIBERNATE_HBM2DDL_AUTO;

	/**
	 * DataSource definition for database connection. Settings are read from the
	 * application.properties file (using the env object).
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		return dataSource;
	}

	/**
	 * Declare the hibernate session factory.
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan("com");

		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
		hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		hibernateProperties.put("hibernate.cache.use_second_level_cache", "false");
		hibernateProperties.put("hibernate.globally_quoted_identifiers", "true");
		hibernateProperties.put("hibernate.id.new_generator_mappings", "true");
		sessionFactoryBean.setHibernateProperties(hibernateProperties);
		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	// @Bean
	// public PlatformTransactionManager transactionManager(EntityManagerFactory
	// emf) {
	// JpaTransactionManager transactionManager = new JpaTransactionManager();
	// transactionManager.setEntityManagerFactory(emf);
	//
	// return transactionManager;
	// }
	//
	// @Bean
	// public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	// LocalContainerEntityManagerFactoryBean em = new
	// LocalContainerEntityManagerFactoryBean();
	// em.setDataSource(dataSource());
	// em.setPackagesToScan(new String[] { "com.customer.microservices" });
	//
	// JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	// em.setJpaVendorAdapter(vendorAdapter);
	// em.setJpaProperties(additionalProperties());
	//
	// return em;
	// }

	
	/**
	 * PersistenceExceptionTranslationPostProcessor is a bean post processor
	 * which adds an advisor to any bean annotated with Repository so that any
	 * platform-specific exceptions are caught and then rethrown as one Spring's
	 * unchecked data access exceptions (i.e. a subclass of
	 * DataAccessException).
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// Properties additionalProperties() {
	// Properties properties = new Properties();
	// properties.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
	// properties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
	// properties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
	// properties.put("hibernate.cache.use_second_level_cache", "false");
	// properties.put("hibernate.cache.use_second_level_cache", "false");
	// properties.put("hibernate.globally_quoted_identifiers", "true");
	// return properties;
	// }

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		GuavaCache cache1 = new GuavaCache("mycustomer", CacheBuilder.newBuilder().build());
		GuavaCache cache2 = new GuavaCache("mycustomers",
				CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build());
		simpleCacheManager.setCaches(Arrays.asList(cache1, cache2));
		return simpleCacheManager;
	}

	// @Bean
	// public CacheManager cacheManager() {
	// ConcurrentMapCacheManager cacheManager = new
	// ConcurrentMapCacheManager("mycustomers", "mycustomer");
	// return cacheManager;
	// }
}

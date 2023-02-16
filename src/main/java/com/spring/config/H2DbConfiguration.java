package com.spring.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "h2EntityManagerFactory",
		transactionManagerRef = "h2TransactionManager",
		basePackages = {"com.spring.repository1"}
		)
public class H2DbConfiguration {
	
	@Primary
	@Bean(name = "h2dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		 return DataSourceBuilder.create().build();
	}
	

	@Bean(name = "entityManagerFactoryBuilder")
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
		System.out.println("----entityManagerFactoryBuilder-------");
	   return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}
	
	@Primary
	@Bean(name = "h2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean h2EntityManagerFactoryBean(@Qualifier("entityManagerFactoryBuilder") EntityManagerFactoryBuilder builder
			, @Qualifier("h2dataSource") DataSource dataSource) {
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
				return builder
						.dataSource(dataSource)
						.properties(properties)
						.packages("com.spring.model1")
						.persistenceUnit("h2")
						.build();
		
	}
	@Bean(name = "h2TransactionManager")
	public PlatformTransactionManager h2TransactionManager(@Qualifier("h2EntityManagerFactory") EntityManagerFactory h2EntityManagerFactory) {
		return new JpaTransactionManager(h2EntityManagerFactory);
		
	}
	
}

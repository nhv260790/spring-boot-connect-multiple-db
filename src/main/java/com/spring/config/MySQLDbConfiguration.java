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
		entityManagerFactoryRef = "mySqlEntityManagerFactory",
				transactionManagerRef = "mySqlTransactionManager",
		basePackages = {"com.spring.repository2"}
		)
public class MySQLDbConfiguration {
	
	@Primary
	@Bean(name = "mySqldataSource")
	@ConfigurationProperties(prefix = "spring.datasource.mysql")
	public DataSource dataSource() {
		 return DataSourceBuilder.create().build();
	}
	

	@Bean(name = "mySqlentityManagerFactoryBuilder")
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
		System.out.println("----mySqlentityManagerFactoryBuilder-------");
	   return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}
	
	@Primary
	@Bean(name = "mySqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBean(@Qualifier("mySqlentityManagerFactoryBuilder") EntityManagerFactoryBuilder builder
			, @Qualifier("mySqldataSource") DataSource dataSource) {
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
				return builder
						.dataSource(dataSource)
						.properties(properties)
						.packages("com.spring.model2")
						.persistenceUnit("mysql")
						.build();
		
	}
	@Bean(name = "mySqlTransactionManager")
	public PlatformTransactionManager mySqlTransactionManager(@Qualifier("mySqlEntityManagerFactory") EntityManagerFactory mySqlEntityManagerFactory) {
		return new JpaTransactionManager(mySqlEntityManagerFactory);
		
	}
	
}

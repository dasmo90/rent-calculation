package de.dasmo90.business.rc.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceConfiguration {

	public static final String DATA_SOURCE = "jdbc/RentCalculationMySqlDataSource";
	public static final String PERSISTENCE_UNIT_NAME = "rc-unit";

	public static final String ENTITY_PACKAGE = "de.dasmo90.business.rc.persistence";

	private DataSource dataSource = new JndiDataSourceLookup().getDataSource(DATA_SOURCE);

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setPackagesToScan(ENTITY_PACKAGE);
		emfb.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emfb.setJpaVendorAdapter(vendorAdapter);
		emfb.setJpaProperties(additionalProperties());

		return emfb;
	}

	private Properties additionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class.getName());
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactoryBean().getObject());
	}
}

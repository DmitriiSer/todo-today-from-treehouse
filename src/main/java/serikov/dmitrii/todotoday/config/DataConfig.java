package serikov.dmitrii.todotoday.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;
import javax.sql.DataSource;

@Configuration
@PropertySource("application.properties")
public class DataConfig {

  @Autowired
  private Environment env;

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

    entityManagerFactory.setPackagesToScan(env.getProperty("app.entity.package"));

    JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);

    entityManagerFactory.setDataSource(dataSource());

    entityManagerFactory.setJpaProperties(getHibernateProperties());

    return entityManagerFactory;
  }

  @Bean
  public DataSource dataSource() {
    BasicDataSource ds = new BasicDataSource();

    ds.setDriverClassName(env.getProperty("datasource.driver"));
    ds.setUrl(env.getProperty("datasource.url"));
    ds.setUsername(env.getProperty("datasource.username"));
    ds.setPassword(env.getProperty("datasource.password"));

    return ds;
  }

  private Properties getHibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//    properties.put("hibernate.implicit_naming_strategy",env.getProperty("hibernate.implicit_naming_strategy"));
//    properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
//    properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    return properties;
  }

}

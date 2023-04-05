package com.laptrinhjavaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com.laptrinhjavaweb.repository"})// khi gặp anitation này y sẽ tự động khởi tạo các đối tượng cần thiết để chúng ta có thể sử dụng các Repository của Spring Data JPA.
@EnableTransactionManagement
public class JPAConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPersistenceUnitName("persistence-data");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springmvcbasicfree");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
    //    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");//để như này khi run có bảng nhưng stop thì mất bảng( tuỳ máy) https://www.youtube.com/watch?v=2zkAzKHkYlo&t=3s 3:25
        //  properties.setProperty("hibernate.hbm2ddl.auto", "update");
        //  properties.setProperty("hibernate.hbm2ddl.auto", "create");
        //https://www.youtube.com/watch?v=7TnvcOAW65o 45:22 tắt mục trên vì ta thêm data vào rồi nếu k tắt thì nó create mất hết dữ liệu
        	properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        properties.setProperty("spring.jpa.properties..hibernate.show_sql","true");
        return properties;
    }
}

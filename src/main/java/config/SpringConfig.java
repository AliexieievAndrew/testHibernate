package config;

import dao.BookDao;
import dao.BookDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import service.BookService;
import service.BookServiceImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
// для обектов в папках сервис и дао искать необходимые бины здесь
//@ComponentScan({"service","dao"})
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public SessionFactory getSessionFactory() throws IOException {

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//        sessionFactoryBean.setPackagesToScan("com.test");

//getHibernateProperties method is a private method

        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.setDataSource(geDataSource());
        sessionFactoryBean.afterPropertiesSet();

        return sessionFactoryBean.getObject();
    }

    @Bean
    public DataSource geDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:sqlserver://localhost:1433");
        dataSource.setUsername("takezo");
        dataSource.setPassword("24893232");
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return dataSource;
    }

    @Bean
    public BookService getUserService() {
        return new BookServiceImpl();
    }
    @Bean
    public BookDao getBookDao() throws IOException {
        return new BookDaoImpl(getSessionFactory());
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() throws IOException{
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory());
        return transactionManager;
    }

    private static Properties getHibernateProperties() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        hibernateProperties.put("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        hibernateProperties.put("hibernate.connection.username","takezo");
        hibernateProperties.put("hibernate.connection.password","24893232");
        hibernateProperties.put("hibernate.connection.url","jdbc:sqlserver://localhost:1433");
        hibernateProperties.put("hibernate.show_sql", false);
        // other properties

        return hibernateProperties;
    }
}

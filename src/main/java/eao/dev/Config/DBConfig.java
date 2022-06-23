package eao.dev.Config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:pom_properties.properties")
public class DBConfig {

    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.url}")
    private String dbURL;

    @Value("${db.login}")
    private String dbLogin;

    @Value("${db.pass}")
    private String dbPass;

    @Value("${db.schema}")
    private String dbSchema;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${show_sql}")
    private String showSQL;

    protected static String hibernateConfigFile = "hibernate.cfg.xml";

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbURL);
        dataSource.setUsername(dbLogin);
        dataSource.setPassword(dbPass);
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getSessionFactory() {

        Properties hibernateProp = new Properties();
        hibernateProp.setProperty("hibernate.default_schema", dbSchema);
        hibernateProp.setProperty("hibernate.show_sql", showSQL);

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setHibernateProperties(hibernateProp);
        factoryBean.setConfigLocation(new ClassPathResource(hibernateConfigFile));
        return factoryBean;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() {
        return new HibernateTransactionManager(Objects.requireNonNull(getSessionFactory().getObject()));
    }

}

package org.core.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

@Configuration //Spring config
@ComponentScan(basePackages = {"org.tutorial", "org.core.config"}) // Spring 掃描託管整個路徑依照專案路徑決定
@MapperScan("org.tutorial.Mapper") // Mybatis Mapper掃描託管依照專案路徑決定，這裡路徑只要到有Mapper的Package
@EnableTransactionManagement // Spring 啟動註解管理，相當於xml的<tx:annotation-driven/>
@PropertySource("classpath:db.properties")
public class AppConfig {
    // JNDI dataSource來源設定
    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String DriverClassName;


    @Bean
    public DataSource dataSource()
            throws IllegalArgumentException, NamingException {
        // JNDI版本
//        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
//        bean.setResourceRef(true);
//        bean.setJndiName("jdbc/practice"); // jdbc/資料庫來源名稱
//        bean.afterPropertiesSet();
//        return (DataSource) bean.getObject();

//        JDBC版本
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(DriverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
//  Hibernate 資料庫來源 設定檔
//    @Bean
//    public SessionFactory sessionFactory() throws IllegalArgumentException, NamingException {
//        return new LocalSessionFactoryBuilder(dataSource())
//                .scanPackages("org.*.model")
//                .addProperties(getHibernateProperties())
//                .buildSessionFactory();
//    }
////   Hibernate 組態設定檔
//    private Properties getHibernateProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", MySQLDialect.class.getName());
//        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("hibernate.format_sql", "true");
//        properties.setProperty("hibernate.highlight_sql", "true");
//        properties.setProperty("hibernate.current_session_context_class", SpringSessionContext.class.getName());
//        return properties;
//    }

    //  Hibernate和Mybatis都一定要加的設定檔TransactionManager
    @Bean
    public TransactionManager transactionManager() throws IllegalArgumentException, NamingException {
        return new DataSourceTransactionManager(dataSource());
        // Hibernate 回傳 HibernateTransactionManager(sessionFactory());
        // Mybatis 回傳 DataSourceTransactionManager(dataSource());
    }

    // Mybaits 資料庫來源設定檔 sqlSessionFactory()
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:Mapper/*"));// Mapper設定檔也必須要設定上去
        sessionFactory.setTypeAliasesPackage("org.tutorial.model.entity");// 一定要設定上去否則找不到Entity
        return sessionFactory.getObject();
    }




    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        return new PaginationInnerInterceptor();
    }


}

package org.core.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.tutorial.model.entity.DeptDO;
import org.tutorial.model.entity.EmpDO;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration //Spring config
@ComponentScan("org.tutorial") // Spring 掃描託管整個路徑依照專案路徑決定
@MapperScan("org.tutorial.Mapper") // Mybatis Mapper掃描託管依照專案路徑決定，這裡路徑只要到有Mapper的Package
@EnableTransactionManagement // Spring 啟動註解管理，相當於xml的<tx:annotation-driven/>
public class AppConfig {
    // JNDI dataSource來源設定
    @Bean
    public DataSource dataSource()
            throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setResourceRef(true);
        bean.setJndiName("jdbc/practice"); // jdbc/資料庫來源名稱
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
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
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setMapperLocations(MapperLocation());// Mapper設定檔也必須要設定上去
        sessionFactory.setTypeAliases(DeptDO.class, EmpDO.class);// 一定要設定上去否則找不到Entity
        return sessionFactory.getObject();
    }

    // 設定Mapper設定檔位置
    public Resource[] MapperLocation() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath*:Mapper/DeptMapper.xml");//若要設定多個只要在加add及檔案位置， classpath:從resources開始
        mapperLocations.add("classpath*:Mapper/EmpMapper.xml");//若要設定多個只要在加add及檔案位置， classpath:從resources開始
        List<Resource> resources = new ArrayList();
        if (mapperLocations != null) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }




}

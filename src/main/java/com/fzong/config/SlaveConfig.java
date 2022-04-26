package com.fzong.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.fzong.other.mapper", sqlSessionTemplateRef = "slaveSqlSessionTemplate")
//注解@ConditionalOnProperty，这个注解能够控制某个configuration是否生效，这里的配置不要动，修改在properties文件中，往上看
@ConditionalOnProperty(prefix = "spring.datasource.slave", name = "enabled", havingValue = "true")
public class SlaveConfig {

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //设置slave数据源mapper文件路径
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/other/*.xml"));
//        bean.setTypeAliasesPackage("com.fzong.**.domain");
        //mybatis plus 需要设置
        MybatisConfiguration config = new MybatisConfiguration();
        // 开启javaBean 成员变量名映射为映射到数据库列名的时候，驼峰命名法到下划线分割命名法则的自动转换功能（）
        config.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(config);
        return bean.getObject();
    }

    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager slaveTransactionManager(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
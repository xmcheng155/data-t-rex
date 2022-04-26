package com.fzong.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.fzong.project.mapper", sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class MasterConfig {

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //设置master数据源mapper文件路径(mybatis 需要设置)
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/project/*.xml"));
//        bean.setTypeAliasesPackage("com.fzong.**.domain");

        //mybatis plus 需要设置
        MybatisConfiguration config = new MybatisConfiguration();
        // 开启javaBean 成员变量名映射为映射到数据库列名的时候，驼峰命名法到下划线分割命名法则的自动转换功能（）
        config.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(config);
        return bean.getObject();
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "masterSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

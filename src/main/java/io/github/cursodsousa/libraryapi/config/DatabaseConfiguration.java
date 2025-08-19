package io.github.cursodsousa.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String passwoed;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //===============================================================================================================
    //DataSource não recomendado para uso em produção
    //@Bean
    //public DataSource dataSource(){
        //DriverManagerDataSource ds = new DriverManagerDataSource();
        //ds.setUrl(url);
        //ds.setUsername(username);
        //ds.setPassword(passwoed);
       // ds.setDriverClassName(driver);
        //return ds;
    //}
    //===============================================================================================================
    //Recomendado
    @Bean
    public DataSource hikaridataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(passwoed);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        config.setMaximumPoolSize(10); //Maximo de conexões liberadas
        config.setMinimumIdle(1); //Tamanho inicial do pool
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000); // 600 milessegundos conexão dura no maximo (10 minutos e depois morre)
        config.setConnectionTimeout(100000); //Timeout para conseguir uma conexão
        config.setConnectionTestQuery("select 1"); // Teste de query

        return new HikariDataSource(config);
    }
    //===============================================================================================================
}

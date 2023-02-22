package com.fundamentosplatzi.springboot.fundamentos.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;

@Configuration
@PropertySource("classpath:connection.properties")
//@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {

	@Value("${value.name}")
	private String name;
	
	@Value("${value.apellido}")
	private String apellido;
	
	@Value("${value.random}")
	private String random;
	
	@Bean
	public MyBeanWithProperties function() {
		return new MyBeanWithPropertiesImplement(name, apellido);
	}
	
	@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@Value("${driver}")
	private String driver;
	
	@Value("${username}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	
	
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create()
		.driverClassName(driver)
		.url(jdbcUrl)
		.username(username)
		.password("").build();
	}
}

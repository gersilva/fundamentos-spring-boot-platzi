package com.fundamentosplatzi.springboot.fundamentos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanImplement;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanTwoImplement;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependencyImplement;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyOperation;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyOperationImplement;

@Configuration
public class MyBeanConfiguration {
	
	@Bean
	public MyBean beanOperation() {
		return new MyBeanTwoImplement();
	}
	
	@Bean
	public MyOperation beanOperationOperation() {
		return new MyOperationImplement();
	}
	
	@Bean
	public MyBeanWithDependency beanOperationWithDependency(MyOperation myOperation) {
		return new MyBeanWithDependencyImplement(myOperation);
	}

}

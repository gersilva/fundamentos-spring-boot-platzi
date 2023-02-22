package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{
	
	Log logger = LogFactory.getLog(MyBeanWithDependencyImplement.class);

	private MyOperation myOperation;
	
	public MyBeanWithDependencyImplement(MyOperation myOperation) {
		this.myOperation = myOperation;
	}

	@Override
	public void printWithDependency() {
		int numero = 1;
		logger.info("Mi número es: " + numero);
		logger.info(myOperation.suma(numero));
		logger.info("Hola desde la implementación de un bean con dependencia");
	}

}

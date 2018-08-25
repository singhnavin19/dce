package com.dce.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHelper implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext newapplicationContext) throws BeansException {
		applicationContext = newapplicationContext;

	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <E extends Object> E getBean(String beanName, Class<E> clazz) {
		return applicationContext.getBean(beanName, clazz);
	}

}

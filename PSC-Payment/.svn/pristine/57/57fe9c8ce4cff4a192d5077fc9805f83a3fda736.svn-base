package cn.wellcare.core.bean;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取服务bean
 * 
 * @author zhaihl
 *
 */
public class ServiceLocator implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	private static ServiceLocator servlocator = null;

	public static ServiceLocator getInstance() {
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			if (servlocator == null) {
				servlocator = (ServiceLocator) applicationContext.getBean(ServiceLocator.class);
			}
		} finally {
			lock.unlock();
		}
		return servlocator;
	}

	public Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public <T> T getBean(String beanName, Class<T> type) {
		return applicationContext.getBean(beanName, type);
	}

	public <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ServiceLocator.applicationContext = applicationContext;
	}

	public static void setServiceLocator(ServiceLocator s) {
		servlocator = s;
	}
}

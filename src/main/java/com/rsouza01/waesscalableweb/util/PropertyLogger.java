package com.rsouza01.waesscalableweb.util;

/**
 * @author Rodrigo Souza (rsouza01)
 *
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;

import com.rsouza01.waesscalableweb.controller.DataDifferenceApiRestController;

public class PropertyLogger implements ApplicationListener<ApplicationPreparedEvent> {

	private Logger log = LoggerFactory.getLogger(DataDifferenceApiRestController.class);

	private ConfigurableEnvironment environment;

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {
		environment = event.getApplicationContext().getEnvironment();
		printProperties();
	}

	public void printProperties() {
		for (EnumerablePropertySource propertySource : findPropertiesPropertySources()) {
			log.info("******* " + propertySource.getName() + " *******");
			String[] propertyNames = propertySource.getPropertyNames();
			Arrays.sort(propertyNames);
			for (String propertyName : propertyNames) {
				String resolvedProperty = environment.getProperty(propertyName);
				String sourceProperty = propertySource.getProperty(propertyName).toString();
				if(resolvedProperty.equals(sourceProperty)) {
					log.info("{}={}", propertyName, resolvedProperty);
				}else {
					log.info("{}={} OVERRIDDEN to {}", propertyName, sourceProperty, resolvedProperty);
				}
			}
		}
	}

	private List<EnumerablePropertySource> findPropertiesPropertySources() {
		List<EnumerablePropertySource> propertiesPropertySources = new LinkedList<>();
		for (PropertySource<?> propertySource : environment.getPropertySources()) {
			if (propertySource instanceof EnumerablePropertySource) {
				propertiesPropertySources.add((EnumerablePropertySource) propertySource);
			}
		}
		return propertiesPropertySources;
	}
}
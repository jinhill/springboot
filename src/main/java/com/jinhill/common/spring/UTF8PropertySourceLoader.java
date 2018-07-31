package com.jinhill.common.spring;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

//use utf-8 load properties, need set src/main/resources/META-INF/spring.factories
//org.springframework.boot.env.PropertySourceLoader=com.jinhill.spring.UTF8PropertySourceLoader
public class UTF8PropertySourceLoader implements PropertySourceLoader {

	@Override
	public String[] getFileExtensions() {
		return new String[] { "properties" };
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, ?> loadProperties(Resource resource) throws IOException {
		System.out.println("loadProperties"+resource.getFilename());
		EncodedResource ecResource = new EncodedResource(resource, "UTF-8");
		return (Map) PropertiesLoaderUtils.loadProperties(ecResource);
	}

	@Override
	public List<PropertySource<?>> load(String name, Resource resource)
			throws IOException {
		Map<String, ?> properties = loadProperties(resource);
		if (properties.isEmpty()) {
			return Collections.emptyList();
		}
		return Collections
				.singletonList(new MapPropertySource(name, (Map<String, Object>) properties));
		
	}
}
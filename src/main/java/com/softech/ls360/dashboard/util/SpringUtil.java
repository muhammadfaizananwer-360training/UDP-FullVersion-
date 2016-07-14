package com.softech.ls360.dashboard.util;

import java.util.Properties;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class SpringUtil {
	
	public static GenericXmlApplicationContext loadSpringContext(String springXmlFile) {
		
		/* In Spring, the ApplicationContext interface is an extension to BeanFactory. In addition to DI services, the
		 * ApplicationContext also provides other services, such as transaction and AOP service, message source for 
		 * internationalization (i18n), and application event handling, to name a few.
		 * 
		 * In developing Spring-based application, it’s recommended that you interact with Spring via the ApplicationContext 
		 * interface.
		 * 
		 * The GenericXmlApplicationContext class implements the ApplicationContext interface and is able to bootstrap Spring’s 
		 * ApplicationContext from the configurations defined in XML files.
		 */
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load(springXmlFile);
		context.refresh();
		
		return context;
		
	} //end of loadSpringContext()
	
	public static Properties loadPropertiesFileFromClassPath(String fileClassPath) {
		
		Properties properties = null;
		
		try {
			
			/**
			 * Often an application needs to access a variety of resources in different forms. You might need to access some 
			 * configuration data stored in a file in the file system, some image data stored in a JAR file on the classpath, or maybe 
			 * some data on a server elsewhere. Spring provides a  unified mechanism for accessing resources in a protocol-independent 
			 * way. This means your application can access a file resource in the same way, whether it is stored in the file  system, in
			 * the classpath, or on a remote server.
			 * 
			 * At the core of Spring’s resource support is the org.springframework.core.io.Resource interface. The Resource interface 
			 * defines ten self-explanatory methods: contentLength(), exists(), getDescription(), getFile(), getFileName(), getURI(), 
			 * getURL(), isOpen(), isReadable(), and lastModified(). In addition to these ten methods, there is one that is not quite so 
			 * self-explanatory: createRelative(). The createRelative() method creates a new Resource instance using a path that is 
			 * relative to the instance on which it is invoked. You can provide your own Resource implementations, but in most cases, you
			 * use one of the built-in implementations for accessing file (the FileSystemResource class), 
			 * classpath (the ClassPathResource class), or URL resources (the UrlResource class).
			 * 
			 * Internally, Spring uses another interface, ResourceLoader, and the default implementation, DefaultResourceLoader, to 
			 * locate and create Resource instances. However, you generally won’t interact with DefaultResourceLoader, instead using 
			 * another ResourceLoader implementation—ApplicationContext.
			 */
			Resource resource = new ClassPathResource(fileClassPath);
			properties = PropertiesLoaderUtils.loadProperties(resource);
			
		} catch (Exception e) {
			System.out.println("Unablle to load properties file from path: " + fileClassPath);
		}
		
		return properties;
		
	} //end of loadPropertiesFileFromClassPath()


} //end of class SpringUtil

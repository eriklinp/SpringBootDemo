package com.example.demo;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/webjars/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/login").setViewName("public/login");
    	registry.addRedirectViewController("/", "/home");
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
    	//
    }
    
    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
    	SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
    	
    	Properties mappings = new Properties();
    	mappings.setProperty("DataAccessException", "dbError");
    	mappings.setProperty("RuntimeException", "error");
    	
    	exceptionResolver.setExceptionMappings(mappings);
    	exceptionResolver.setDefaultErrorView("error");
    	
    	
    	return exceptionResolver;
    }
    
    @Autowired
    private MessageSource messageSource;
    
    @Override
    public Validator getValidator() {
    	LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
    	factory.setValidationMessageSource(messageSource);
    	return factory;
    }
    
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // When overriding default behavior, you need to add default(/) as well as added static paths(/webapp).
//
//        // src/main/resources/static/...
//        registry
//            //.addResourceHandler("/**") // « /css/myStatic.css
//            .addResourceHandler("/static/**") // « /static/css/myStatic.css
//            .addResourceLocations("classpath:/static/") // Default Static Loaction
//            .setCachePeriod( 3600 )
//            .resourceChain(true) // 4.1
//            .addResolver(new GzipResourceResolver()) // 4.1
//            .addResolver(new PathResourceResolver()); //4.1
//
//        // src/main/resources/templates/static/...
//        registry
//            .addResourceHandler("/templates/**") // « /templates/style.css
//            .addResourceLocations("classpath:/templates/static/");
//
//        // Do not use the src/main/webapp/... directory if your application is packaged as a jar.
//        registry
//            .addResourceHandler("/webapp/**") // « /webapp/css/style.css
//            .addResourceLocations("/");
//
//        // File located on disk
//        registry
//            .addResourceHandler("/system/files/**")
//            .addResourceLocations("file:///D:/");
//    }    
}

package ritektw;

import java.io.IOException;

import javax.sql.DataSource;

import org.activiti.rest.common.application.ContentTypeResolver;
import org.activiti.rest.common.application.DefaultContentTypeResolver;
import org.activiti.rest.service.api.RestResponseFactory;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RestController;



@Configuration
//@ComponentScan(basePackages = { "org.activiti.rest.diagram", "org.activiti.rest"}, 
//	includeFilters = {
//        @Filter(type = FilterType.ANNOTATION, classes = {
//                RestController.class }) }, useDefaultFilters = false, lazyInit = false)
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration{
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource activitiDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            PlatformTransactionManager transactionManager,
            SpringAsyncExecutor springAsyncExecutor) throws IOException {

        return baseSpringProcessEngineConfiguration(
                activitiDataSource(),
                transactionManager,
                springAsyncExecutor);
    }
	
	@Bean
    public RestResponseFactory restResponseFactory(){
        return new RestResponseFactory();
    }
    @Bean
    public ContentTypeResolver contentTypeResolver(){
        return new DefaultContentTypeResolver();
    }
    

}

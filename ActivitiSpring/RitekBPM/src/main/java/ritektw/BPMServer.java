package ritektw;

import javax.sql.DataSource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.data.impl.MybatisGroupDataManager;
import org.activiti.engine.impl.persistence.entity.data.impl.MybatisUserDataManager;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;



@SpringBootApplication(exclude = org.activiti.spring.boot.SecurityAutoConfiguration.class)
@ComponentScan("org.activiti.rest, ritektw")
public class BPMServer {
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(BPMServer.class);
	
	public BPMServer() {
		
	}
	
//	@Autowired
//	private SpringProcessEngineConfiguration processEngineConfiguration;
	
//    @Autowired
//    private InMemoryUserDetailsManager userManager;
    
//    @Autowired
//    private RuntimeService runtimeService;
//
//    @Autowired
//    private TaskService taskService;
//
//    @Autowired
//    private IdentityService identityService;
//
//    @Autowired
//    SpringProcessEngineConfiguration config;
    
//    @Bean
//    InitializingBean processEngineInitializer() {
//    	logger.info("enter processEngineInitializer");
//    	
//        return new InitializingBean() {
//            public void afterPropertiesSet() throws Exception {
//            	processEngineConfiguration.setIdentityService(identityService);
//            	
//                processEngineConfiguration.setUserEntityManager(
//                		new SpringSecurityUserManager(processEngineConfiguration, 
//                				new MybatisUserDataManager(processEngineConfiguration), userManager));
//                
//                processEngineConfiguration.setGroupEntityManager(
//                		new SpringSecurityGroupManager(processEngineConfiguration, 
//                				new MybatisGroupDataManager(processEngineConfiguration)));
//            }
//        };
//    }

	@Bean
	InitializingBean usersAndGroupsInitializer(IdentityService identityService) {
		logger.info("enter usersAndGroupsInitializer");
		
	    return new InitializingBean() {
	        public void afterPropertiesSet() throws Exception {
	        	logger.info("identityService");
	        	
//	            User user = identityService.newUser("activiti_user");
//	            user.setPassword("pass");
//	            identityService.saveUser(user);
//	 
//	            Group group = identityService.newGroup("user");
//	            group.setName("ROLE_USER");
//	            group.setType("USER");
//	            identityService.saveGroup(group);
//	            identityService.createMembership(user.getId(), group.getId());
	        }
	    };
	}
//    @Primary
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource database() {
//        return DataSourceBuilder.create().build();
//    }
    

    public static void main(String[] args) {

    	SpringApplication.run(BPMServer.class, args);
    }
    
    
//    @Bean
//    public CommandLineRunner init(final RepositoryService repositoryService,
//                                  final RuntimeService runtimeService,
//                                  final TaskService taskService) {
//
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                System.out.println("Number of process definitions : "
//                	+ repositoryService.createProcessDefinitionQuery().count());
//                System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
//                
//                runtimeService.startProcessInstanceByKey("oneTaskProcess");
//                
//                System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
//            }
//        };
//    }
    
}

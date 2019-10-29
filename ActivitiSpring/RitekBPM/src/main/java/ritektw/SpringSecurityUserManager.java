package ritektw;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntityManagerImpl;
import org.activiti.engine.impl.persistence.entity.data.UserDataManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

public class SpringSecurityUserManager extends UserEntityManagerImpl{
	private UserDetailsManager userManager;
	 
    public SpringSecurityUserManager(
    		
      ProcessEngineConfigurationImpl processEngineConfiguration,
      UserDataManager userDataManager, 
      UserDetailsManager userManager) {
    	
    	super(processEngineConfiguration, userDataManager);
    	
        this.userManager = userManager;
    }
    
    @Override
    public UserEntity findById(String userId) {
    	
        UserDetails userDetails = userManager.loadUserByUsername(userId);
        if (userDetails != null) {
            UserEntityImpl user = new UserEntityImpl();
            user.setId(userId);
            return user;
        }
        return null;
    }

}

package ritektw;

import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;

import org.activiti.engine.impl.persistence.entity.GroupEntityManagerImpl;
import org.activiti.engine.impl.persistence.entity.data.GroupDataManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

public class SpringSecurityGroupManager extends GroupEntityManagerImpl {
	
    private UserDetailsManager userManager;

    public SpringSecurityGroupManager(ProcessEngineConfigurationImpl processEngineConfiguration, GroupDataManager groupDataManager) {
    	
        super(processEngineConfiguration, groupDataManager);
    }
    
}

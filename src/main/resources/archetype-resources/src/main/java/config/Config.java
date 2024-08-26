#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Config {
    @Autowired
    private Environment env;
    public String get(String key){
        return env.getProperty(key);
    }
    public String get(String key,String defaultValue){
        return env.getProperty(key,defaultValue);
    }
    public <T> T get(String key,Class<T> cls){
        return env.getProperty(key,cls);
    }
    public <T> T get(String key,T defaultValue, Class<T> cls){
        return env.getProperty(key,cls,defaultValue);
    }
    public String[] getActiveProfiles(){
        return env.getActiveProfiles();
    }
}

package pl.edu.agh.wp.orm.configuration;

import pl.edu.agh.wp.orm.session.SessionFactory;
import pl.edu.agh.wp.orm.session.DefaultSessionFactory;

import java.util.Properties;

public class Configuration {

    Properties properties;

    public Configuration (){
        this.properties = new Properties();
    }

    public Configuration addProperties(String key, String value){
        properties.setProperty(key,value);
        return this;
    }

    public SessionFactory buildSessionFactory() {
        return new DefaultSessionFactory(properties);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

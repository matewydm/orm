package pl.edu.agh.wp.orm.configuration;

import pl.edu.agh.wp.orm.dto.repo.AnnotationDatabaseObjectMapper;
import pl.edu.agh.wp.orm.exception.ORMException;
import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.factory.AnnotationORMFactory;
import pl.edu.agh.wp.orm.mapper.factory.ORMFactory;
import pl.edu.agh.wp.orm.session.SessionFactory;
import pl.edu.agh.wp.orm.session.DefaultSessionFactory;

import java.util.Properties;

public class Configuration {

    public static final String DB_URL = "db_url";
    public static final String USER = "user";
    public static final String PACKAGE = "package";
    public static final String DRIVER_CLASS = "driver_class";
    public static final String PASSWORD = "password";
    private Properties properties;
    private boolean enabledAnnotation;

    public Configuration (){
        this.properties = new Properties();
    }

    public Configuration addDriverClass (String driverClass) {
        return addProperties(DRIVER_CLASS,driverClass);
    }

    public Configuration addDatabaseUrl(String dbUrl) {
        return addProperties(DB_URL,dbUrl);
    }

    public Configuration addUser (String user) {
        return addProperties(USER,user);
    }

    public Configuration addPassword (String password){
        return addProperties(PASSWORD, password);
    }

    public Configuration setAnnotationParsing(Boolean val){
        enabledAnnotation = val;
        return this;
    }
    public Configuration addScannedPackage(String pck){
        return addProperties(PACKAGE,pck);
    }
    private Configuration addProperties(String key, String value){
        properties.setProperty(key,value);
        return this;
    }

    public SessionFactory buildSessionFactory() {
        validateProperties();
        if(enabledAnnotation){
            ORMFactory ormFactory = new AnnotationORMFactory();
            TableMapper mapper = ormFactory.getMapper();
            return new DefaultSessionFactory(properties,
                    new AnnotationDatabaseObjectMapper(properties.getProperty("package"),mapper));
        }else{
            throw new ORMException("Enable Annotation processing");
        }
    }

    private void validateProperties(){
        //TODO sprawdz czy sa wszystkie jak nie to rzuc wyjatek
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

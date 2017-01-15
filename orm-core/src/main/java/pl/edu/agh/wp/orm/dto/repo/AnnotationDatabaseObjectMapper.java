package pl.edu.agh.wp.orm.dto.repo;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import pl.ed.agh.wp.orm.annotations.DBTable;
import pl.edu.agh.wp.orm.dto.DBTableObject;
import pl.edu.agh.wp.orm.mapper.TableMapper;

public class AnnotationDatabaseObjectMapper implements DatabaseObjectMapper {

    private ClassPathScanningCandidateComponentProvider scanner;
    private TableMapper mapper;
    private String basePackage;

    public AnnotationDatabaseObjectMapper(String basePackage,TableMapper mapper) {
        this.basePackage = basePackage;
        this.mapper = mapper;
        scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(DBTable.class));
    }
    
    @Override
    public EntitiesRepository getEntities() throws ClassNotFoundException {

        EntitiesRepository entities =  EntitiesRepository.getInstance();
     for(BeanDefinition bean :scanner.findCandidateComponents(basePackage)){
         Class clazz  = Class.forName(bean.getBeanClassName());
         DBTableObject table = mapper.getTable(clazz);
         entities.addEntity(table);
     }

        return  entities;
    }
}

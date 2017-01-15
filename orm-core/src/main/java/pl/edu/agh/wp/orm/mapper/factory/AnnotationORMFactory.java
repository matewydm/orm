package pl.edu.agh.wp.orm.mapper.factory;

import pl.edu.agh.wp.orm.mapper.TableMapper;
import pl.edu.agh.wp.orm.mapper.annotation.*;

public class AnnotationORMFactory implements ORMFactory {

    @Override
    public TableMapper getMapper() {
        return new AnnotationTableMapper(new AnnotationColumnMapper(),new AnnotationIdMapper(),new AnnotationManyToOneMapper(),new AnnotationOneToManyMapper());
    }
}

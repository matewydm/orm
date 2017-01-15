package pl.edu.agh.wp.orm.mapper.factory;

import pl.edu.agh.wp.orm.mapper.TableMapper;

public interface ORMFactory {
    TableMapper getMapper();

}

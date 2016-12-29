package pl.edu.agh.wp.orm.dto.repo;

public interface DatabaseObjectMapper {

    EntitiesRepository getEntities() throws ClassNotFoundException;
}

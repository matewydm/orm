package pl.edu.agh.wp.orm.criterion.queries;

public interface Criterion {
    void buildQuery();

    String toSqlQuery();


}

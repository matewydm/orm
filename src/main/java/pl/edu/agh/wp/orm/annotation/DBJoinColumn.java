package pl.edu.agh.wp.orm.annotation;

public @interface DBJoinColumn {
    String name() default "";
    String tableName() default "";
    boolean nullable() default false;
    boolean unique() default false;

}

package pl.edu.agh.wp.orm.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DBColumn {
    String name() default "";
    boolean nullable() default true;
    long maxLength() default 255;
    boolean unique() default false;
    int scale() default 0;
    int precision() default 0;





}

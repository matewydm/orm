package pl.ed.agh.wp.orm.annotations;


import pl.ed.agh.wp.orm.annotations.enums.GenerationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DBGeneratedValue {
    GenerationType strategy() default  GenerationType.AUTO;
}

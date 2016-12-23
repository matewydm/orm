package pl.edu.agh.wp.orm.annotations;

import pl.edu.agh.wp.orm.converter.types.TypeConverter;

//TODO to ogarnąć żeby konwertować magicznie
public @interface Type {
    Class<? extends TypeConverter> converter();

}


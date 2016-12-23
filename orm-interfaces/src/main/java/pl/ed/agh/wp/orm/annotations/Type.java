package pl.ed.agh.wp.orm.annotations;


import pl.ed.agh.wp.orm.annotations.converter.types.TypeConverter;

//TODO to ogarnąć żeby konwertować magicznie
public @interface Type {
    Class<? extends TypeConverter> converter();

}


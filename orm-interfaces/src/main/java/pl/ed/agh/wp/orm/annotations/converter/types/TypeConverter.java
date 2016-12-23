package pl.ed.agh.wp.orm.annotations.converter.types;


import pl.ed.agh.wp.orm.annotations.enums.DatabaseTypes;

//TODO zrobis magicznie jakos zeby jeden obiekt Javy był
//Trzba zrobic na te podstawowe typy danych w javie
// klikoma kolumnami w DB
public interface TypeConverter {

    Class getObjectClass();

    DatabaseTypes getType();

    String getAsString(Object obj);

    Object getObject(String dbfield);

}

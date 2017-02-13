package pl.edu.agh.wp.orm.creator;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseCreatorTest {

    @Test
    public void testCreate() throws Exception {
        DatabaseCreator db = new DatabaseCreator();
        db.createDatabase();

    }
}
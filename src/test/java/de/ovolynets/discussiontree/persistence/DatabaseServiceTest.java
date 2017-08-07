package de.ovolynets.discussiontree.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import de.ovolynets.discussiontree.persistence.impl.DatabaseServiceImpl;

public class DatabaseServiceTest {

    private final DatabaseService databaseService = new DatabaseServiceImpl();

    @Test
    public void addAndGet() {
        int id = databaseService.addNew("test");
        assert databaseService.get(id).get().getId() == id;
    }

// @Test
// public void delete() {
//
// }

}

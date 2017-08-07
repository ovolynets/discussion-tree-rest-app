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

    @Test
    public void addTwoGetOne() {
        databaseService.addNew("test1");
        databaseService.addNew("test2");
        assert databaseService.get(2).get().getText().equals("test2");
    }

    @Test
    public void delete() {
        databaseService.addNew("test1");
        databaseService.addNew("test2");
        databaseService.delete(1);
        assert databaseService.get(2).get().getText().equals("test2");
        assert !databaseService.get(1).isPresent();
    }

    @Test
    public void addReply() {
        int idPost1 = databaseService.addNew("test1");
        int idPost2 = databaseService.addNew("test2");
        Integer idReply1 = databaseService.addReply(idPost1, "reply1").get();
        Integer idReply11 = databaseService.addReply(idReply1, "reply11").get();

        assert databaseService.get(idReply1).get().getText().equals("reply1");
        assert databaseService.get(idReply11).get().getText().equals("reply11");
    }

}

package myapp;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FakeAllowanceDbTest {

    @Test public void name() throws Exception {
        AllowanceDb db = new FakeAllowanceDb();
        db.addChore("gloria", "vacuum");
        db.addChore("jwilson", "feed indie");
        db.addChore("jwilson", "feed ash");
        db.addChore("gloria", "feed bowie");

        assertEquals(Arrays.asList("vacuum", "feed bowie"), db.getAllChores("gloria"));
    }
}
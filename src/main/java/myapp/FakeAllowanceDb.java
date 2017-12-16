package myapp;

import java.util.ArrayList;
import java.util.List;

public class FakeAllowanceDb implements AllowanceDb {
    public static final AllowanceDb INSTANCE = new FakeAllowanceDb();
    static {
        INSTANCE.addChore("gloria", "hang christmas lights");
        INSTANCE.addChore("gloria", "make cocoa");
        INSTANCE.addChore("jwilson", "pick up doggy poo");
    }


    List<Chore> allChores = new ArrayList<>();

    @Override
    public void addChore(String username, String chore) {
        allChores.add(new Chore(username, chore));
    }

    @Override
    public List<String> getAllChores(String username) {
        List<String> result = new ArrayList<>();

        for (Chore chore : allChores) {
            if (!username.equals(chore.username))
                continue;
            result.add(chore.chore);
        }

        return result;
    }

    static class Chore {
        final String username;
        final String chore;

        public Chore(String username, String chore) {
            this.username = username;
            this.chore = chore;
        }
    }
}

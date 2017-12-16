package myapp;

import java.util.List;

interface AllowanceDb {
    void addChore(String username, String chore);

    List<String> getAllChores(String username);
}

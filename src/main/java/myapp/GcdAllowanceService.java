package myapp;

import java.util.List;

public class GcdAllowanceService implements AllowanceService {
    @Override
    public UserId signUp(String name) {
        return null;
    }

    @Override
    public ChildId addChild(UserId userId, String childName) {
        return null;
    }

    @Override
    public Chore addChore(UserId userId, String childName, String description, float rate) {
        return null;
    }

    @Override
    public Chore submitChore(UserId userId, ChoreId choreId) {
        return null;
    }

    @Override
    public Chore approveChore(UserId userId, ChoreId choreId) {
        return null;
    }

    @Override
    public Chore rejectChore(UserId userId, ChoreId choreId) {
        return null;
    }

    @Override
    public List<Child> listChildren(UserId userId) {
        return null;
    }

    @Override
    public List<Chore> listChores(UserId userId) {
        return null;
    }
}

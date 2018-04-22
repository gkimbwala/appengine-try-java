package myapp;

import java.util.List;

public interface AllowanceService {

    UserId signUp(String name);

    ChildId addChild(UserId userId, String childName);

    Chore addChore(UserId userId, String childName, String description, float rate);

    Chore submitChore(UserId userId, ChoreId choreId);

    Chore approveChore(UserId userId, ChoreId choreId);

    Chore rejectChore(UserId userId, ChoreId choreId);

    List<Child> listChildren(UserId userId);

    List<Chore> listChores(UserId userId);
}


package myapp;

public interface AllowanceService {

    UserId signUp(String name);

    ChildId addChild(UserId userId, String childName);
}


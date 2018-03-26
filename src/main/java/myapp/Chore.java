package myapp;

public class Chore {
    public final UserId userId;
    public final ChoreId choreId;
    public final String description;
    public final float rate;
    public final State state;

    public Chore(UserId userId, ChoreId choreId, String description, float rate, State state) {
        this.userId = userId;
        this.choreId = choreId;
        this.description = description;
        this.rate = rate;
        this.state = state;
    }

    enum State {
        ASSIGNED, SUBMITTED, APPROVED,
    }

}

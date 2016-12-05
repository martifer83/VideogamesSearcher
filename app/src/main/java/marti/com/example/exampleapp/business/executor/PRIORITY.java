package marti.com.example.exampleapp.business.executor;

/**
 * Created by mferrando on 03/06/16.
 */
public enum PRIORITY {

    CRITICAL(0), VERY_HIGH(20), HIGH(40), DEFAULT(60), LOW(80), VERY_LOW(100);

    private final int priority;

    PRIORITY(int priority) {
        this.priority = priority;
    }

    public int getValue() {
        return priority;
    }


}

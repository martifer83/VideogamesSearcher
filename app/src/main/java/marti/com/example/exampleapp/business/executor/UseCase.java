package marti.com.example.exampleapp.business.executor;

/**
 * Created by mferrando on 03/06/16.
 */
/**
 * Useful for packaging any task implementation and run on a task pool
 * with multithreading and priority support.
 */
public abstract class UseCase extends Task<Void> {

    /**
     * Builder.
     * @param id The task identifier.
     * @param priority The task priority.
     */
    public UseCase(long id, PRIORITY priority) {
        super(id);
        init(this, priority);
    }

    /**
     * Builder, with the task default priority and identifier value zero.
     */
    public UseCase() {
        super(0);
        init(this, PRIORITY.DEFAULT);
    }

    /* (non-Javadoc)
     * @see r2b.apps.lib.taskmanager.Task#call()
     */
    @Override
    public final Void call() throws Exception {
        return super.call();
    }

}
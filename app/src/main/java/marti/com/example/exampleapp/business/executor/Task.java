package marti.com.example.exampleapp.business.executor;

/**
 * Created by mferrando on 03/06/16.
 */

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * This class is useful for packaging any task implementation and
 * run on a task pool with multithreading and priority support.
 *
 * A task can be any portion of code, it is like an android AsyncTask but
 * with the possibility of return to main thread or not, return info or not,
 * block the execution thread while this is waiting for the result or not.
 *
 * @param <V> Void or any other object.
 */
abstract class Task<V> implements Callable<V> {

    /**
     * The FutureTasks<V> to run.
     * It extends a priority field.
     */
    FutureTask<V> priorityFutureTask;
    /**
     * Task identifier.
     */
    private final long id;

    /**
     * Builder.
     */
    public Task(long id) {
        super();

        // Set task identifier
        this.id = id;
    }

    /**
     * Initializes the class.
     * @param callable The callable class to call.
     * @param priority The priority.
     */
    final void init(Callable<V> callable, PRIORITY priority) {

        if (callable == null) {
            throw new IllegalArgumentException("callable argument is null.");
        }

        // Builds future task with priority
        priorityFutureTask = new PriorityFutureTask<V>(callable);
        // Sets priority
        ((PriorityFutureTask<V>) priorityFutureTask).setPriority(priority.getValue());
    }

    /**
     * Attempts to cancel execution of this task. This attempt will fail if the task has already completed,
     * has already been cancelled, or could not be cancelled for some other reason. If successful, and this
     * task has not started when cancel is called, this task should never run. If the task has already
     * started, then the mayInterruptIfRunning parameter determines whether the thread executing this
     * task should be interrupted in an attempt to stop the task.
     *
     * @param mayInterruptIfRunning True if the thread executing this task should be interrupted;
     * otherwise, in-progress tasks are allowed to complete.
     * @return False if the task could not be cancelled, typically because it has already
     * completed normally; true otherwise.
     */
    public boolean cancel(boolean mayInterruptIfRunning) {

        if (priorityFutureTask == null) {
            throw new IllegalStateException("priorityFutureTask is null.");
        }

        return priorityFutureTask.cancel(mayInterruptIfRunning);
    }

    /**
     * Executes the task on background.
     * Add here your background code.
     * @return The result of the task.
     * @throws Exception
     */
    protected abstract V doInBackground() throws Exception;

    /**
     * Get the task priority.
     * @return The task priority.
     */
    protected final int getPriority() {

        if (priorityFutureTask == null) {
            throw new IllegalArgumentException("priorityFutureTask is null.");
        }

        return ((PriorityFutureTask<V>) priorityFutureTask).getPriority();
    }

    /**
     * Get task identifier.
     * @return The identifier, should be unique.
     */
    public long getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public V call() throws Exception {

        if (priorityFutureTask == null) {
            throw new IllegalStateException("priorityFutureTask is null.");
        }

        // Useful to identify the task on debug
        Thread.currentThread().setName(this.getClass().getSimpleName() + "<" + String.valueOf(id) + ">");

        return doInBackground();

    }

}


package marti.com.example.exampleapp.business.executor;

/**
 * Created by mferrando on 03/06/16.
 */

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Manages all concerned of threads, queues, priorities; on task executions.
 */
public final class TaskManager {

    /**
     * The number of threads to keep in the pool, even if they are idle.
     * By default the available processors multiplied by two.
     */
    private static final int DEFAULT_CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    /**
     * The initial capacity of the task queue.
     */
    private static final int INITIAL_QUEUE_CAPACITY = 10; // Extract from android AsyncTask source code
    /**
     * When the number of threads is greater than the core, this is the maximum time
     * that excess idle threads will wait for new tasks before terminating.
     */
    private static final int KEEP_ALIVE = 1; // Seconds // Extracted from android AsyncTask source code
    /**
     * Time to wait until all threads are finish when shutdown executor.
     */
    private static final long AWAIT_TERMINATION_TIME = 500L;

    /**
     * Manage termination and tracking progress of one or more tasks.
     */
    private ExecutorService executor;

    private static final TaskManager instance = new TaskManager();

    /**
     * Singleton.
     * Initializes the thread pool by default with the available processors multiplied by two.
     */
    public static TaskManager getInstance() {
        return instance;
    }

    /**
     * Builder.
     * Initializes the thread pool with the DEFAULT_CORE_POOL_SIZE.
     */
    private TaskManager() {
        init(DEFAULT_CORE_POOL_SIZE);
    }

    /**
     * Builder.
     * Initializes the thread pool with the argument size. The treads are reusing.
     * @param threadPoolSize The number of threads to keep in the pool, even if they are idle.
     * DEFAULT_CORE_POOL_SIZE will be set for negative values.
     */
    public TaskManager(int threadPoolSize) {
        if(threadPoolSize > 0) {
            init(threadPoolSize);
        }
        else {
            init(DEFAULT_CORE_POOL_SIZE);
        }

    }

    /**
     * Initializes the thread pool with the argument size. The treads are reusing.
     * Call this method more than one time resets previous pool.
     * @param threadPoolSize The number of threads to keep in the pool, even if they are idle.
     */
    private void init(int threadPoolSize) {

        // An unbounded queue
        final BlockingQueue<Runnable> taskPriorityBlockingQueue =
                new PriorityBlockingQueue<Runnable>(INITIAL_QUEUE_CAPACITY, new PriorityFutureTaskComparator());

        // Create and init the executor with a fixed thread pool and an unbounded priority blocking queue
        executor =
                new ThreadPoolExecutor(
                        threadPoolSize,
                        threadPoolSize,
                        KEEP_ALIVE,
                        TimeUnit.SECONDS,
                        taskPriorityBlockingQueue);

    }

    /**
     * Executes a task.
     * @param task The task to execute. Do nothing if is null or task manager is shutdown.
     */
    public void execute(Task<?> task) {

        if (task != null && !executor.isShutdown()) {
            executor.execute(task.priorityFutureTask);
        }

    }

    /**
     * Shutdown the manager, call this method invalidates task completion listeners/observers.
     * Invocation has no additional effect if already shut down.
     *
     * @param mayInterruptIfRunning True attempts to stop all actively executing tasks,
     * halts the processing of waiting tasks, this does not wait for actively executing
     * tasks to terminate. False initiates an orderly shutdown in which previously
     * submitted tasks are executed.
     */
    public void shutdown(boolean mayInterruptIfRunning) {

        if(!executor.isShutdown()) {

            if(mayInterruptIfRunning) {
                executor.shutdownNow();
            }
            else {
                executor.shutdown();
            }

            try {
                // Wait until all threads are finish
                executor.awaitTermination(AWAIT_TERMINATION_TIME, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.e("TASK_MANAGER", "InterruptedException");
            }

        }

    }

    /* (non-Javadoc)
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() throws Throwable {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow();
        }
        super.finalize();
    }

}

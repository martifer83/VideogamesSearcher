package marti.com.example.exampleapp.di;

/**
 * Created by mferrando on 20/03/2018.
 */

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 *
 * @param <C> the Component type parameter
 */

public interface HasComponent<C> {
    C getComponent();
}


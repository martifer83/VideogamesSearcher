package marti.com.example.exampleapp.common.error;

public interface ErrorManagerInterface {
    ErrorMessage processError(Throwable throwable);
}

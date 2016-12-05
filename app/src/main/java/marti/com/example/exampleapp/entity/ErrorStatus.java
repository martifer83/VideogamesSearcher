package marti.com.example.exampleapp.entity;

/**
 * Created by mferrando on 25/05/16.
 */
public class ErrorStatus {

    /** Http status, or error or exception type */
    private int mCode;
    /** Http error type or localized popup title */
    private String mTitle;
    /** Localized string for the user and getted from the backend */
    private String mMessage;

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }



    @Override
    public String toString() {
        return "ErrorStatus{" +
                "mCode=" + mCode +
                ", mTitle='" + mTitle + '\'' +
                ", mMessage='" + mMessage + '\'' +
                '}';
    }
}


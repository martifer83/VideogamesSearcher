package marti.com.example.exampleapp.common.error;

import android.support.annotation.StringRes;

import marti.com.example.exampleapp.R;

public class ErrorMessage {

    private static final int NO_TITLE = -1;
    private static final int DEFAULT_MESSAGE = -1;//R.string.error_server;

    @StringRes
    private final int titleResId;
    @StringRes
    private final int messageResId;

    private ErrorMessage() {
        this(NO_TITLE, DEFAULT_MESSAGE);
    }

    public ErrorMessage(int messageResId) {
        this(NO_TITLE, messageResId);
    }

    public ErrorMessage(int titleResId, int messageResId) {
        this.titleResId = titleResId;
        this.messageResId = messageResId;
    }

    public static ErrorMessage getDefault() {
        return new ErrorMessage();
    }

    public int getTitleResId() {
        return titleResId;
    }

    public int getMessageResId() {
        return messageResId;
    }

    public boolean hasTitle() {
        return titleResId != NO_TITLE;
    }
}

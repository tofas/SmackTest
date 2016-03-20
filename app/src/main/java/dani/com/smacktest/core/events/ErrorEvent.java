package dani.com.smacktest.core.events;

import android.util.Log;

/**
 * Created by flamingo on 17/3/16.
 */
public class ErrorEvent implements XmppEvent{

    public String tag;
    public String message;

    public ErrorEvent(String tag, String message) {
        this.tag = tag;
        this.message = message;
    }


    @Override
    public void print() {
        Log.e(tag, message);
    }
}


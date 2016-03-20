package dani.com.smacktest.core.events;

import org.jivesoftware.smack.XMPPConnection;

import android.util.Log;

/**
 * Created by flamingo on 17/3/16.
 */
public class AuthenticationEvent implements XmppEvent{

    private String tag;
    private boolean resumed;

    public AuthenticationEvent(String tag, boolean resumed) {
        this.tag = tag;
        this.resumed = resumed;
    }

    @Override
    public void print() {
        Log.v(tag, "Login: SUCCESS");
    }
}

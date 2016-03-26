package dani.com.smacktest.core.events;

import android.util.Log;

/**
 * Created by flamingo on 17/3/16.
 */
public class ConnectionEvent implements XmppEvent {

    private String tag;

    public ConnectionEvent(String tag) {
        this.tag = tag;
    }

    @Override
    public void print() {
        Log.v(tag, "XMPP Connected!!!");
    }
}

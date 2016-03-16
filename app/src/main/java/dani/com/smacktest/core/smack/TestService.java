package dani.com.smacktest.core.smack;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by flamingo on 16/3/16.
 */
public class TestService extends Service {

    public static ConnectivityManager connectivityManager;
    public static TestXMPP xmpp;

    private static final String DOMAIN = "xmpp.jp";
    private static final String USERNAME = "khushi";
    private static final String PASSWORD = "password";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new TestServiceBinder<>(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        xmpp = TestXMPP.getInstance(this, DOMAIN, USERNAME, PASSWORD);
        xmpp.connect();
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags,
            final int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(final Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        xmpp.connection.disconnect();
    }

    public static boolean isNetworkConnected() {
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}

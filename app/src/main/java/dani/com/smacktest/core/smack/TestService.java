package dani.com.smacktest.core.smack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

import dani.com.smacktest.TestSmackApplication;
import dani.com.smacktest.core.events.ConnectionEvent;
import dani.com.smacktest.core.events.ErrorEvent;

/**
 * Created by flamingo on 16/3/16.
 */
public class TestService extends Service {

    public static ConnectivityManager connectivityManager;

    @Inject
    TestXMPP xmpp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new TestServiceBinder<>(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        ((TestSmackApplication) getApplication()).getXMPPComponent().inject(this);
        EventBus.getDefault().register(this);
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
        xmpp.disconnect();
    }

    public static boolean isNetworkConnected() {
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    @Subscribe
    public void onXMPPMessageReceived(ConnectionEvent event) {
        event.print();
    }

    @Subscribe
    public void onErrorMessageReceived(ErrorEvent event) {
        event.print();
    }
}

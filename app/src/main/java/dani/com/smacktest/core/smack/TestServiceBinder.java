package dani.com.smacktest.core.smack;

import android.os.Binder;

import java.lang.ref.WeakReference;

/**
 * Created by flamingo on 16/3/16.
 */
public class TestServiceBinder<S> extends Binder {

    private final WeakReference<S> mService;

    public TestServiceBinder(final S service) {
        mService = new WeakReference<S>(service);
    }

    public S getService() {
        return mService.get();
    }
}

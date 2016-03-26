package dani.com.smacktest;

import android.app.Application;

import dani.com.smacktest.core.dagger.AppComponent;
import dani.com.smacktest.core.dagger.AppModule;
import dani.com.smacktest.core.dagger.DaggerAppComponent;
import dani.com.smacktest.core.dagger.DaggerXMPPComponent;
import dani.com.smacktest.core.dagger.XMPPComponent;
import dani.com.smacktest.core.dagger.XMPPModule;

/**
 * Created by flamingo on 17/3/16.
 */
public class TestSmackApplication extends Application {

    private XMPPComponent XMPPComponent;
    private AppComponent appComponent;

    private static final String USERNAME = "khushi";
    private static final String PASSWORD = "password";

    @Override
    public void onCreate() {
        super.onCreate();

        XMPPComponent = DaggerXMPPComponent.builder()
                .xMPPModule(new XMPPModule(USERNAME, PASSWORD))
                .build();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    public XMPPComponent getXMPPComponent() {
        return XMPPComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}

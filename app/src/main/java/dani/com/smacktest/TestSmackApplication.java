package dani.com.smacktest;

import android.app.Application;

import dani.com.smacktest.core.dagger.AppModule;
import dani.com.smacktest.core.dagger.DaggerXMPPComponent;
import dani.com.smacktest.core.dagger.XMPPComponent;
import dani.com.smacktest.core.dagger.XMPPModule;

/**
 * Created by flamingo on 17/3/16.
 */
public class TestSmackApplication extends Application {

    private XMPPComponent XMPPComponent;

    private static final String USERNAME = "khushi";
    private static final String PASSWORD = "password";

    @Override
    public void onCreate() {
        super.onCreate();

        XMPPComponent = DaggerXMPPComponent.builder()
                .appModule(new AppModule(this))
                .xMPPModule(new XMPPModule(USERNAME, PASSWORD))
                .build();
    }

    public XMPPComponent getXMPPComponent() {
        return XMPPComponent;
    }

}

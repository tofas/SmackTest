package dani.com.smacktest.core.dagger;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dani.com.smacktest.core.smack.TestService;
import dani.com.smacktest.core.smack.TestXMPP;

/**
 * Created by flamingo on 17/3/16.
 */
@Module
public class XMPPModule {

    private String serverAddress = "xmpp.jp";
    private String loginUser;
    private String passwordUser;

    public XMPPModule(String loginUser, String passwordUser) {
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
    }

    @Provides
    @Singleton
    TestXMPP provideTestXMPP() {
        return TestXMPP.getInstance(serverAddress, loginUser, passwordUser);
    }
}

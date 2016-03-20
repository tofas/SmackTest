package dani.com.smacktest.core.dagger;

import junit.framework.Test;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Component;
import dani.com.smacktest.core.smack.TestService;
import dani.com.smacktest.core.smack.TestXMPP;

/**
 * Created by flamingo on 17/3/16.
 */
@Singleton
@Component(modules={AppModule.class, XMPPModule.class})
public interface XMPPComponent {
    void inject(TestService service);
}

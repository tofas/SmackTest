package dani.com.smacktest.core.dagger;

import javax.inject.Singleton;

import dagger.Component;
import dani.com.smacktest.core.base.BaseView;

/**
 * Created by flamingo on 21/3/16.
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(BaseView baseView);
}

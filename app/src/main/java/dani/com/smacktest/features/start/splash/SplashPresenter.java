package dani.com.smacktest.features.start.splash;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import javax.inject.Inject;

import dani.com.smacktest.TestSmackApplication;
import dani.com.smacktest.core.base.BasePresenter;
import dani.com.smacktest.core.smack.TestService;

/**
 * Created by flamingo on 20/3/16.
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    private static SplashPresenter instance;


    public static SplashPresenter getInstance(SplashView splashView) {
        if (instance == null) {
            instance = new SplashPresenter(splashView);
        }

        return instance;
    }

    public SplashPresenter(SplashView splashView) {

        setView(splashView);

    }

    public void initService() {
        Intent intent = new Intent(getView().getActivity(), TestService.class);
        getView().getActivity().startService(intent);

        boolean isLogged = getView().getSharedPreferences().getBoolean("IS_LOGED", false);

        if(isLogged) {
            getView().navigateToChat();
        } else {
            getView().navigateToLogin();
        }
    }
}

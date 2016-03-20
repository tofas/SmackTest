package dani.com.smacktest.features.start.splash;

import android.content.Intent;

import dani.com.smacktest.core.base.BasePresenter;
import dani.com.smacktest.core.smack.TestService;

/**
 * Created by flamingo on 20/3/16.
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    private static SplashPresenter instance;

    public static SplashPresenter getInstance() {
        if(instance == null) instance = new SplashPresenter();

        return instance;
    }

    public SplashPresenter() {

    }

    public void initService() {
        Intent intent = new Intent(getView().getActivity(), TestService.class);
        getView().getActivity().startService(intent);
    }
}

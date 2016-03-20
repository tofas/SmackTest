package dani.com.smacktest.features.start.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import dani.com.smacktest.R;
import dani.com.smacktest.core.base.BaseView;

/**
 * Created by flamingo on 20/3/16.
 */
public class SplashView extends BaseView {

    private static SplashView instance;
    private SplashPresenter presenter;

    public static SplashView newInstance() {
        if(instance == null) instance = new SplashView();

        return instance;
    }

    public SplashView() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = SplashPresenter.getInstance();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.initService();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.splash_view;
    }
}

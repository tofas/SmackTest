package dani.com.smacktest.features.start;

import android.os.Bundle;

import dani.com.smacktest.core.base.BaseActivity;
import dani.com.smacktest.features.start.splash.SplashView;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFragment(SplashView.newInstance());
    }
}

package dani.com.smacktest.features.start;

import android.content.Intent;
import android.os.Bundle;

import dani.com.smacktest.core.base.BaseActivity;
import dani.com.smacktest.features.chat.ChatActivity;
import dani.com.smacktest.features.start.login.LoginView;
import dani.com.smacktest.features.start.splash.SplashView;

public class StartActivity extends BaseActivity implements StartNavigationInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFragment(SplashView.newInstance());
    }

    @Override
    public void onSingedIn() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onNotSignedIn() {
        loadFragment(LoginView.newInstance());
    }
}

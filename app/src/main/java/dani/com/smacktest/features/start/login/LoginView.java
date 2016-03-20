package dani.com.smacktest.features.start.login;

import dani.com.smacktest.R;
import dani.com.smacktest.core.base.BaseView;

/**
 * Created by flamingo on 20/3/16.
 */
public class LoginView extends BaseView {

    public static LoginView instance;

    public static LoginView newInstance() {
        if(instance == null) instance = new LoginView();

        return instance;
    }

    public LoginView() {}

    @Override
    protected int getFragmentLayout() {
        return R.layout.login_view;
    }
}

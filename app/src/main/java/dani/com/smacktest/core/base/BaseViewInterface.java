package dani.com.smacktest.core.base;

import android.content.SharedPreferences;

/**
 * Created by dani on 31/10/15.
 */
public interface BaseViewInterface {

    enum TypeSnackbar {
        TYPE_POSITIVE, TYPE_ERROR
    }

    void showLoading();
    void hideLoading();

    void setFragmentContent(int layoutId);

    void showSnackbar(String displayName, TypeSnackbar TYPE);

    SharedPreferences getSharedPreferences();
}

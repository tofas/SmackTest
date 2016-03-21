package dani.com.smacktest.core.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dani.com.smacktest.R;
import dani.com.smacktest.TestSmackApplication;

/**
 * Created by dani on 31/10/15.
 */
public abstract class BaseView extends Fragment implements BaseViewInterface {

    private View view;
    private View inflated;

    @Inject
    SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_base, container, false);
        setFragmentContent(getFragmentLayout());
        injectViews(view);

        ((TestSmackApplication)getActivity().getApplication()).getAppComponent().inject(this);

        return view;
    }

    protected abstract int getFragmentLayout();

    private void injectViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setFragmentContent(int layoutId) {
        ViewStub stub = (ViewStub) view.findViewById(R.id.fragment_content);
        stub.setLayoutResource(layoutId);
        inflated = stub.inflate();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return preferences;
    }

    @Override
    public void showSnackbar(String displayName, TypeSnackbar TYPE) {
        Snackbar snackbar = Snackbar.make(getView(), displayName, Snackbar.LENGTH_LONG);

        int color = -1;

        switch(TYPE) {
            case TYPE_POSITIVE:
                color = getActivity().getResources().getColor(android.R.color.holo_green_light);
                break;
            case TYPE_ERROR:
                color = getActivity().getResources().getColor(android.R.color.holo_red_light);
                break;
        }

        snackbar.getView()
                .setBackgroundColor(color);
        snackbar.show();
    }

}

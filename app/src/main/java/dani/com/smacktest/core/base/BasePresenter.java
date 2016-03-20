package dani.com.smacktest.core.base;

/**
 * Created by dani on 31/10/15.
 */
public class BasePresenter<S extends BaseViewInterface>{

    private S mBaseView;

    public void onError(String error) {

    }

    public S getView() {
        return mBaseView;
    }

    public void setView(S view) {
        mBaseView = view;
    }
}

package janmejai.com.mvpproject.Presenter;

import android.content.Context;

import javax.inject.Inject;

import janmejai.com.mvpproject.MainActivity;

/**
 * Created by hitesh-trisys on 9/12/17.
 */

public class MainPresenter {

    private final Context context;

    public MainPresenter(Context context){
        this.context=context;
    }

    public void onShowPostButtonClick(MainScreen mainScreen){
        mainScreen.launchNextScreen();
    }

    public void onshowErrorButtonClick(MainScreen mainScreen){
        mainScreen.showErrorMessage();
    }

    public void onShowMapButtonClick(MainScreen mainScreen) {
        mainScreen.goToMapActivity();
    }
    public void onShowRetrofitButtonClick(MainScreen mainScreen) {
        mainScreen.goToRetrofitActivity();
    }

}

package janmejai.com.mvpproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import janmejai.com.mvpproject.Activity.RetrofitActivity;
import janmejai.com.mvpproject.Presenter.MainPresenter;
import janmejai.com.mvpproject.Presenter.MainScreen;

public class MainActivity extends AppCompatActivity implements MainScreen {


    @Bind(R.id.button)
    Button button;
//    @Bind(R.id.buttonid)
//    Button buttonid
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);
    }

    @OnClick(R.id.button)
    public void goTo(){
        mainPresenter.onShowPostButtonClick(this);
    }

    @OnClick(R.id.buttonid)
    public void showError(){
        mainPresenter.onshowErrorButtonClick(this);
    }

    @OnClick(R.id.mapbttn)
    public void goToMap(){
        mainPresenter.onShowMapButtonClick(this);
    }
    @OnClick(R.id.retrofit)
    public void goToRetrofit(){
        mainPresenter.onShowRetrofitButtonClick(this);
    }

    @Override
    public void launchNextScreen() {
        Intent intent = new Intent(MainActivity.this, NextScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this,"message is comming fine",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMapActivity() {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToRetrofitActivity() {
        Intent intent = new Intent(MainActivity.this, RetrofitActivity.class);
        startActivity(intent);
    }

}

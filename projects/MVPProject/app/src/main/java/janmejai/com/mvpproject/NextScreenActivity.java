package janmejai.com.mvpproject;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import janmejai.com.mvpproject.Presenter.NextScreen;
import janmejai.com.mvpproject.Presenter.NextScreenPresenter;

public class NextScreenActivity extends AppCompatActivity implements NextScreen {

    @Bind(R.id.circularvp)
    ViewPager viewPager;
    private NextScreenPresenter nextScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_screen);
        ButterKnife.bind(this);
        nextScreenPresenter=new NextScreenPresenter(this);
        nextScreenPresenter.addListener(this);
        nextScreenPresenter.setViewPagerAdapter(viewPager,getSupportFragmentManager());
    }
}

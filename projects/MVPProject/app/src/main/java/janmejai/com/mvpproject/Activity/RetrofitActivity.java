package janmejai.com.mvpproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import janmejai.com.mvpproject.Interface.CommonScreen;
import janmejai.com.mvpproject.Model.EmployeeResponse;
import janmejai.com.mvpproject.Presenter.RetrofitPresenter;
import janmejai.com.mvpproject.R;

public class RetrofitActivity extends AppCompatActivity {

    private RetrofitPresenter retrofitPresenter;

    @Bind(R.id.syncbttn)
    Button synbttn;
    @Bind(R.id.datasync)
    TextView textVw;
    private EmployeeResponse employeeResponse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
        retrofitPresenter=new RetrofitPresenter(this);
    }
    @OnClick(R.id.syncbttn)
    public void goTo(){
        employeeResponse=retrofitPresenter.syncData();
        if(employeeResponse!=null) {
            textVw.setText(employeeResponse.getMessage());
        }

    }

}

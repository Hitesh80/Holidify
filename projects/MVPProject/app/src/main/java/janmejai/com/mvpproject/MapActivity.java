package janmejai.com.mvpproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import janmejai.com.mvpproject.Presenter.MapPresenter;
import janmejai.com.mvpproject.Presenter.MapScreen;

public class MapActivity extends AppCompatActivity implements MapScreen {

    @Bind(R.id.gobttn)
    Button goBttn;
    @Bind(R.id.location_et)
    EditText locationEt;
    private MapPresenter mapPresenter;
    private GoogleMap mGoogleMap;
    private boolean available;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapPresenter = new MapPresenter(this);
        mapPresenter.addListener(this);

        available = mapPresenter.googleMapAvailabilty();
        if (available) {
            Toast.makeText(this, "Perfect..", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_map);
            ButterKnife.bind(this);
            mapPresenter.inItMap(mGoogleMap);
        } else {
//            layout not avlable
        }
    }

    @OnClick(R.id.gobttn)
    public void getGeo() {
        String location = locationEt.getText().toString();
        try {
            mapPresenter.getGeoData(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       mapPresenter.goToSelectedMenu(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

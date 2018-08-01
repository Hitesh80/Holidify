package io.google.holidify;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {
  private List<Destination> destinationList = new ArrayList<>();
  private List<Destination> destinationListfromdb = new ArrayList<>();
  private LocationManager locationManager;
  private double currentLat = 12.9166;
  private double currentLng = 77.6101;
  private ListView destinationLv;
  private DestinationAdapter destinationAdapter;
  private boolean permission=false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    destinationLv = findViewById(R.id.destination_list);
    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    readDataFromCsv();
    getDestinationList();
  }

  @Override protected void onResume() {
    super.onResume();
   checkpermission();
  }

  @Override protected void onPause() {
    super.onPause();
    checkpermission();
  }

  private void checkpermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
          != PackageManager.PERMISSION_GRANTED
          && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
          != PackageManager.PERMISSION_GRANTED) {

        requestPermissions(new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        }, 100);

        return;
      }else {
        permission=true;
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
      }
    } else {
      permission=true;
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    switch (requestCode) {
      case 100:
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
          permission=true;
          if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
              != PackageManager.PERMISSION_GRANTED
              && ActivityCompat.checkSelfPermission(this,
              Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            permission=false;
            return;
          }
          locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000*5, 2, this);
          destinationAdapter.notifyDataSetChanged();
        }

    }
  }

  private void getDestinationList() {
    destinationListfromdb=HolidifyDatabase.getInstance(this).getDestinationDao().getAllDestinations();
    if(destinationListfromdb!=null&&destinationListfromdb.size()>0){
      destinationAdapter=new DestinationAdapter(destinationListfromdb);
      destinationLv.setAdapter(destinationAdapter);
    }
  }

  private void readDataFromCsv() {
    Destination destination;

    InputStream inputStream=getResources().openRawResource(R.raw.android_assignment);
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
    String data;
    try {
      while ((data=bufferedReader.readLine())!=null){
          String[] token=data.split(",");
          destination=new Destination();destination.setPlaceCode(token[0]);
        destination.setPlaceName(token[1]);
        if(token[2].length()>0) {
          destination.setLat(Double.parseDouble(token[2]));
        }else {
          destination.setLat(0.0);
        }
        if(token[3].length()>0) {
          destination.setLng(Double.parseDouble(token[3]));
        }else {
          destination.setLng(0.0);
        }
        destination.setCityImagePath(token[4]);
        destination.setCityIntro(token[5]);
        destinationList.add(destination);

      }
    }catch (IOException i){
      i.printStackTrace();
    }

    HolidifyDatabase.getInstance(this).getDestinationDao().insert(destinationList);

  }

  @Override public void onLocationChanged(Location location) {
    if(location!=null) {
      currentLat = location.getLatitude();
      currentLng = location.getLongitude();
      destinationAdapter.notifyDataSetChanged();
    }

  }

  @Override public void onStatusChanged(String s, int i, Bundle bundle) {
    Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
  }

  @Override public void onProviderEnabled(String s) {
    Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
  }

  @Override public void onProviderDisabled(String s) {
    Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
  }

  class DestinationAdapter extends BaseAdapter {
    private final List<Destination> destinations;

    DestinationAdapter(List<Destination> destinations) {
      this.destinations = destinations;
    }
    @Override public int getCount() {
      return destinations.size();
    }

    @Override public Destination getItem(int position) {
      return destinations.get(position);
    }

    @Override public long getItemId(int position) {
      return position;
    }

    @Override public View getView(final int position, View convertView, ViewGroup parent) {
      if (convertView == null) {
        convertView = getLayoutInflater().inflate(R.layout.destination_item, parent, false);
      }
      ImageView image = convertView.findViewById(R.id.image);
      TextView distance=convertView.findViewById(R.id.distanceText);
      if(permission==true) {
        distance.setText(
            getDistance(destinations.get(position).getLat(), destinations.get(position).getLng())
                + "km");
      }else {
        distance.setText("distance cant find");
      }
      Glide.with(MainActivity.this)
          .load(destinations.get(position).getCityImagePath())
          .into(image);

      image.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          Intent intent = new Intent(MainActivity.this, DestinationDetails.class);
          intent.putExtra("placename",destinations.get(position).getPlaceName());
          intent.putExtra("imageurl",destinations.get(position).getCityImagePath());
          intent.putExtra("content",destinations.get(position).getCityIntro());
          startActivity(intent);
        }
      });

      return convertView;
    }
  }

  private String getDistance(double lat,double lng) {
    Location startPoint=new Location("locationA");
    startPoint.setLatitude(currentLat);
    startPoint.setLongitude(currentLng);
    Location endPoint=new Location("locationA");
    endPoint.setLatitude(lat);
    endPoint.setLongitude(lng);
    double distance=startPoint.distanceTo(endPoint);
    double distanceinkm =distance/1000.0;
    return distanceinkm+"";
  }
}

package janmejai.com.mvpproject.Presenter;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import janmejai.com.mvpproject.MapActivity;
import janmejai.com.mvpproject.R;

import static android.content.Intent.ACTION_POWER_DISCONNECTED;

/**
 * Created by hitesh-trisys on 9/15/17.
 */
public class MapPresenter implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private final MapScreen mapScreen;
    private MapActivity mapActivity;
    private MapFragment mapFragment;
    private GoogleMap mGoogleMap;
    private String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest locationRequest;
    private String locality;

    public MapPresenter(MapActivity mapScreen) {
        this.mapScreen = mapScreen;
    }

    public boolean googleMapAvailabilty() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(mapActivity);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(mapActivity, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(mapActivity, "can't connect to play services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void addListener(MapActivity mapActivity) {
        this.mapActivity = mapActivity;
    }

    public void inItMap(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mapFragment = (MapFragment) mapActivity.getFragmentManager().findFragmentById(R.id.fragment2);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        //   gotoLtLn(26.476918,80.319146);
        //  goToLocationZoom(26.476918,80.319146,15);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(mapActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mapActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(mapActivity, permission, PERMISSION_REQUEST_CODE);

                return;
            }


        }
        // mGoogleMap.setMyLocationEnabled(true);
        //to go users location using googleApi
        mGoogleApiClient = new GoogleApiClient.Builder(mapActivity)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(MapPresenter.this)
                .addOnConnectionFailedListener(MapPresenter.this)
                .build();
        mGoogleApiClient.connect();

    }

    private void goToLocationZoom(double v, double v1, float i) {
        LatLng latLng = new LatLng(v, v1);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, i);
        mGoogleMap.moveCamera(cameraUpdate);
    }

    private void gotoLtLn(double lat, double lang) {
        LatLng latLng = new LatLng(lat, lang);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
        mGoogleMap.moveCamera(cameraUpdate);

    }

    public void getGeoData(String location) throws IOException {

        Location location1=new Location(location);
//        Geocoder geocoder = new Geocoder(mapActivity);
//        List<Address> list = geocoder.getFromLocationName(location, 1);
//        Address address = list.get(0);
//        locality = address.getLocality();
//        Toast.makeText(mapActivity, locality, Toast.LENGTH_SHORT).show();
//        double lat = address.getLatitude();
//        double lng = address.getLongitude();
        double lat = location1.getLatitude();
        double lng = location1.getLongitude();

        goToLocationZoom(lat, lng, 15);

    }


    public void goToSelectedMenu(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.mapTypeNone:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            case R.id.mapTypeNormal:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.mapTypeHybrid:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.mapTypesatellite:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.mapTypeTerrain:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            default:
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);
        if (ActivityCompat.checkSelfPermission(mapActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mapActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, MapPresenter.this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        if(location==null){
            Toast.makeText(mapActivity,"cant get the current location",Toast.LENGTH_SHORT).show();
        }else {
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            Geocoder geocoder = new Geocoder(mapActivity);
            List<Address> list = null;
            try {
                list = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(list!=null) {
                Address address = list.get(0);
                locality = address.getLocality();
            }else {

               new getData().execute(location);
            }
            CameraUpdate update=CameraUpdateFactory.newLatLngZoom(ll,15);
            mGoogleMap.animateCamera(update);
            MarkerOptions markerOptions=new MarkerOptions()
                                            .title(locality)
                                            .position(ll);
            mGoogleMap.addMarker(markerOptions);
        }

    }
    private String fetchCityNameUsingGoogleMap(Location location) {
        String googleMapUrl = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + location.getLatitude() + ","
                + location.getLongitude() + "&sensor=false&language=fr";


        try {
            StringBuffer result1=new StringBuffer();
            URL url = new URL(googleMapUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {

                result1.append(line);
            }
            JSONObject googleMapResponse = new JSONObject(result1.toString());

            // many nested loops.. not great -> use expression instead
            // loop among all results
            JSONArray results = (JSONArray) googleMapResponse.get("results");
            for (int i = 0; i < results.length(); i++) {
                // loop among all addresses within this result
                JSONObject result = results.getJSONObject(i);
                if (result.has("address_components")) {
                    JSONArray addressComponents = result.getJSONArray("address_components");
                    // loop among all address component to find a 'locality' or 'sublocality'
                    for (int j = 0; j < addressComponents.length(); j++) {
                        JSONObject addressComponent = addressComponents.getJSONObject(j);
                        if (result.has("types")) {
                            JSONArray types = addressComponent.getJSONArray("types");

                            // search for locality and sublocality
                            String cityName = null;

                            for (int k = 0; k < types.length(); k++) {
                                if ("locality".equals(types.getString(k)) && cityName == null) {
                                    if (addressComponent.has("long_name")) {
                                        cityName = addressComponent.getString("long_name");
                                    } else if (addressComponent.has("short_name")) {
                                        cityName = addressComponent.getString("short_name");
                                    }
                                }
                                if ("sublocality".equals(types.getString(k))) {
                                    if (addressComponent.has("long_name")) {
                                        cityName = addressComponent.getString("long_name");
                                    } else if (addressComponent.has("short_name")) {
                                        cityName = addressComponent.getString("short_name");
                                    }
                                }
                            }
                            if (cityName != null) {
                                return cityName;
                            }
                        }
                    }
                }
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }
    class getData extends AsyncTask<Location, String, String> {
        @Override
        protected String doInBackground(Location... params) {
           locality=fetchCityNameUsingGoogleMap(params[0]);
            return "0";
        }

    }
}

package io.google.holidify;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.io.StringReader;
@Entity(tableName = "destination")
class Destination {
  @PrimaryKey
  @NonNull
  private String placeCode;
  private String placeName;
  private double lat;
  private double lng;
  private String cityImagePath;
  private String cityIntro;

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
  }

  public String getPlaceCode() {
    return placeCode;
  }

  public void setPlaceCode(String placeCode) {
    this.placeCode = placeCode;
  }

  public String getPlaceName() {
    return placeName;
  }

  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }



  public String getCityImagePath() {
    return cityImagePath;
  }

  public void setCityImagePath(String cityImagePath) {
    this.cityImagePath = cityImagePath;
  }

  public String getCityIntro() {
    return cityIntro;
  }

  public void setCityIntro(String cityIntro) {
    this.cityIntro = cityIntro;
  }
}

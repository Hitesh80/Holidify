package io.google.holidify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class DestinationDetails extends AppCompatActivity {

  private ImageView detailIv;
  private TextView placecontentTv;
  private TextView placeTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_destination_details);


    detailIv=findViewById(R.id.detailimage);
    Glide.with(DestinationDetails.this)
        .load(getIntent().getExtras().getString("imageurl"))
        .into(detailIv);
    placeTv=findViewById(R.id.placetext);
    placeTv.setText(getIntent().getExtras().getString("placename"));
    placecontentTv=findViewById(R.id.placecontent);
    placecontentTv.setText(getIntent().getExtras().getString("content"));



  }
}

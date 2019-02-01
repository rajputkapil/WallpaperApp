package com.wallpapers.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wallpapers.app.R;

public class ShowDetail extends AppCompatActivity {

    ImageView imageView;
    public static final String IMAGE_URL = "ImageUrl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        imageView = findViewById(R.id.show_detail_image);
        String url = getIntent().getStringExtra(IMAGE_URL);

        Picasso.get()
                .load(url)
                .into(imageView);

    }
}

package com.example.project_1_app_empty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        ImageView imageView = (ImageView) findViewById(R.id.image_view);

//        getSupportActionBar();
//        getSupportActionBar().setTitle("Full Screen Image");

        Intent i = getIntent();
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);
        imageView.setImageResource(imageAdapter.imageArray[position]);
    }
}
package com.example.project_1_app_empty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PhoneClickActivity extends AppCompatActivity {

    TextView name;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_click);

        name = (TextView)findViewById(R.id.card);
    }
}
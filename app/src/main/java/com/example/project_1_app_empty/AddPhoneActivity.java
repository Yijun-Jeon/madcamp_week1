package com.example.project_1_app_empty;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;



public class AddPhoneActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameView;
    private EditText phoneView;
    private Button addBtn;
    private Fragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        nameView = (EditText) findViewById(R.id.edit_name);
        phoneView = (EditText) findViewById(R.id.edit_phone);
        addBtn = (Button) findViewById(R.id.btn_add);

        addBtn.setOnClickListener(this);

        //fragment1 = (Fragment1) getIntent().getParcelableExtra("data");
    }

    @Override
    public void onClick(View view) {
        if (view == addBtn) {
            String name = nameView.getText().toString();
            String phone = phoneView.getText().toString();

            if (name == null || name.equals("")) {
                Toast t = Toast.makeText(this, "이름이 입력되지 않았습니다. ", Toast.LENGTH_SHORT);
                t.show();
            } else {
                //fragment1.writeData(new PhoneBook(name,phone));
                Intent i = new Intent(this, MainActivity.class);
                Toast t = Toast.makeText(this,"새로운 연락처가 등록되었습니다.",Toast.LENGTH_SHORT);
                t.show();
                startActivity(i);
            }
        }
    }
}
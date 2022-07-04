package com.example.project_1_app_empty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditPhoneActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nameView;
    private EditText phoneView;
    private Button editBtn;
    private ArrayList<PhoneBook> phoneBooks;
    private PhoneBook edit_data;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone);

        nameView = (EditText) findViewById(R.id.edit_name);
        phoneView = (EditText) findViewById(R.id.edit_phone);
        editBtn = (Button) findViewById(R.id.btn_edit);

        editBtn.setOnClickListener(this);

        phoneBooks = (ArrayList<PhoneBook>) getIntent().getSerializableExtra("data");
        position = (int) getIntent().getExtras().getInt("position");

        edit_data = phoneBooks.get(position);

        nameView.setText(edit_data.getName());
        phoneView.setText(edit_data.getPhone());
    }

    @Override
    public void onClick(View view) {
        if(view == editBtn){
            String name = nameView.getText().toString();
            String phone = phoneView.getText().toString();
            phone.replaceAll("-","");
            System.out.println(phone);

            PhoneBook new_data = new PhoneBook(name,phone);

            if (name == null || name.equals("")) {
                Toast t = Toast.makeText(this, "이름이 입력되지 않았습니다. ", Toast.LENGTH_SHORT);
                t.show();
            } else{
                phoneBooks.set(position,new_data);
                File file = new File(getFilesDir()+"linked_phonebook.json");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    Gson gson = new Gson();
                    gson.toJson(phoneBooks,fileWriter);
                    fileWriter.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Intent i = new Intent(this, MainActivity.class);
                Toast t = Toast.makeText(this,"연락처 수정이 완료되었습니다.",Toast.LENGTH_SHORT);
                t.show();
                finish();
                startActivity(i);
            }
        }
    }

    // 뒤로가기 클릭 처리
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        finish();
        startActivity(intent);
    }
}
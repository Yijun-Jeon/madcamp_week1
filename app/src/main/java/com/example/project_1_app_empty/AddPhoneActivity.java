package com.example.project_1_app_empty;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class AddPhoneActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameView;
    EditText phoneView;
    Button addBtn;
    JsonWriter f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        nameView = (EditText) findViewById(R.id.edit_name);
        phoneView = (EditText) findViewById(R.id.edit_phone);
        addBtn = (Button) findViewById(R.id.btn_add);

        addBtn.setOnClickListener(this);
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
                /*String FILE_NAME = "test.json";

                File file = new File(this.getExternalFilesDir("external"),FILE_NAME);
                FileOutputStream out = null;

                // write
                try{
                    out = new FileOutputStream(file,true);
                    f = new JsonWriter(new OutputStreamWriter(out));
                    f.beginObject().name("name").value(name).name("phone").value(phone);
                    f.endObject();
                    f.endArray().close();
                    f.flush();

                    Toast t = Toast.makeText(this,"새로운 연락처가 등록되었습니다.",Toast.LENGTH_SHORT);
                    t.show();
                }catch (IOException e){e.printStackTrace();}*/

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(name,phone);
                PhoneBook data = new PhoneBook(name,phone);

                try {
                    //FileWriter fw = new FileWriter(getFilesDir()+"phonebook2.json");

                    //fw.append("O");
                    //gson.toJson(data,fw);
                    //gson.toJson(jsonObject,fw);

                    Writer wt = new FileWriter("phonebook.json");
                    gson.toJson()

                    fw.flush();
                    fw.close();

                    Toast t = Toast.makeText(this,"새로운 연락처가 등록되었습니다.",Toast.LENGTH_SHORT);
                    t.show();
                }catch (IOException e){e.printStackTrace();}
            }
        }
    }
}
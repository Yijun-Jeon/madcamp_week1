package com.example.project_1_app_empty;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Fragment1 extends Fragment {

    private ArrayList<PhoneBook> mPhonebook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button button;
        View view;
        RecyclerView recyclerView;
        RecyclerAdapter recyclerAdapter;
        CardView cardView;

        File file = new File(getContext().getFilesDir()+"test.json");
        file.delete();
        if(file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                Type type = new TypeToken<ArrayList<PhoneBook>>() {
                }.getType();
                Gson gson = new Gson();
                mPhonebook = gson.fromJson(fileReader, type);
                fileReader.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            mPhonebook = new ArrayList<PhoneBook>();
            String json;
            try {
                InputStream is = getActivity().getAssets().open("phonebook.json");

                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                json = new String(buffer, "UTF-8");
                JSONArray jsonArray = new JSONArray(json);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    mPhonebook.add(new PhoneBook(obj.getString("name"), obj.getString("phone")));
                }
            }catch (Exception e){e.printStackTrace();}

            try {
                FileWriter fileWriter = new FileWriter(file);
                Gson gson = new Gson();
                gson.toJson(mPhonebook,fileWriter);
                fileWriter.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        view = inflater.inflate(R.layout.fragment1, container, false);
        button = view.findViewById(R.id.button_f1);
        button.setOnClickListener(new ClickListener(this));

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(mPhonebook,getActivity());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerAdapter.setMyPhoneBook(mPhonebook);
        return view;
    }

/*    public void writeData(PhoneBook data){
        mPhonebook.add(data);
        File file = new File(getContext().getFilesDir()+"test.json");
        try {
            FileWriter fileWriter = new FileWriter(file);
            Gson gson = new Gson();
            gson.toJson(mPhonebook,fileWriter);
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public class ClickListener implements View.OnClickListener {
        private Fragment1 fragment1;
        public ClickListener(Fragment1 fragment1){
            this.fragment1 = fragment1;
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "새 연락처를 생성합니다", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(),AddPhoneActivity.class);
            //intent.putExtra("data", (Parcelable) fragment1);
            startActivity(intent);
        }
    }
}
package com.example.project_1_app_empty;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
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
import java.util.Collections;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class Fragment1 extends Fragment {

    private ArrayList<PhoneBook> mPhonebook;
    private File JSON;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button button_add;
        View view;
        RecyclerView recyclerView;
        RecyclerAdapter recyclerAdapter;
        JSON = new File(getContext().getFilesDir()+"linked_phonebook.json");

        makeJSON();

        view = inflater.inflate(R.layout.fragment1, container, false);
        button_add = view.findViewById(R.id.button_f1);
        button_add.setOnClickListener(new ClickListener());

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(mPhonebook,getActivity());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerAdapter.setMyPhoneBook(mPhonebook);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            PhoneBook deletedData = null;
            boolean delete = true;

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        deletedData = mPhonebook.get(position);
                        mPhonebook.remove(position);
                        recyclerAdapter.notifyItemRemoved(position);
                        Snackbar.make(recyclerView,deletedData.getName(),Snackbar.LENGTH_LONG)
                                .setAction("되돌리기", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mPhonebook.add(position,deletedData);
                                        recyclerAdapter.notifyItemInserted(position);
                                    }
                                }).show();
                        break;
                    case ItemTouchHelper.RIGHT:
                        writeJSON();
                        Intent intent_edit = new Intent(getActivity(),EditPhoneActivity.class);
                        intent_edit.putExtra("data", mPhonebook);
                        intent_edit.putExtra("position",position);
                        startActivity(intent_edit);
                        getActivity().finish();
                        break;
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent))
                        .addSwipeLeftActionIcon(R.drawable.delete)
                        .addSwipeRightActionIcon(R.drawable.edit)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(getContext(),R.color.recycler_view_item_swipe_left_background))
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }
    public void makeJSON(){
        if(JSON.exists())
            readJSON();
        else{
            updateJSON();
            writeJSON();
        }
    }
    public void readJSON(){
        try {
            FileReader fileReader = new FileReader(JSON);
            Type type = new TypeToken<ArrayList<PhoneBook>>() {
            }.getType();
            Gson gson = new Gson();
            mPhonebook = gson.fromJson(fileReader, type);
            Collections.sort(mPhonebook);
            fileReader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateJSON(){
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
    }
    public void writeJSON(){
        try {
            FileWriter fileWriter = new FileWriter(JSON);
            Gson gson = new Gson();
            gson.toJson(mPhonebook,fileWriter);
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        readJSON();
    }

    public class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
                writeJSON();
                Intent intent_new = new Intent(getActivity(),AddPhoneActivity.class);
                intent_new.putExtra("data", mPhonebook);
                startActivity(intent_new);
                getActivity().finish();
            }
        }
    }
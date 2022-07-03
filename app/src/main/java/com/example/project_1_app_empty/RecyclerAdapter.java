package com.example.project_1_app_empty;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<PhoneBook> myPhoneBook;
    private FragmentActivity context;

    public RecyclerAdapter(ArrayList<PhoneBook> myPhoneBook, FragmentActivity activity){
        this.myPhoneBook = myPhoneBook;
        context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(myPhoneBook.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Custom_dialog custom_dialog = new Custom_dialog(myPhoneBook.get(position));
                custom_dialog.show(context.getSupportFragmentManager(),"Test");
            }
        });
    }

    @Override
    public int getItemCount() {
        return myPhoneBook.size();
    }

    public void setMyPhoneBook(ArrayList<PhoneBook> list){
        myPhoneBook = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
        }

        void onBind(PhoneBook item){
            name.setText(item.getName());
            phone.setText(item.getPhone());
        }
    }
}

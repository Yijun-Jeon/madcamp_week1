package com.example.project_1_app_empty;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private ArrayList<PhoneBook> myPhoneBook;
    private ArrayList<PhoneBook> myPhoneBookAll;
    private FragmentActivity context;

    public RecyclerAdapter(ArrayList<PhoneBook> myPhoneBook, FragmentActivity activity){
        this.myPhoneBook = myPhoneBook;
        this.myPhoneBookAll = new ArrayList<>(myPhoneBook);
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

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<PhoneBook> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty())
                filteredList.addAll(myPhoneBookAll);
            else{
                for(PhoneBook data: myPhoneBookAll){
                    if(data.getName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(data);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }
        //run on a ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            myPhoneBook.clear();
            myPhoneBook.addAll((Collection<? extends PhoneBook>) filterResults.values);
            notifyDataSetChanged();
        }
    };

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

package com.example.partydistributed.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partydistributed.R;
import com.example.partydistributed.utils.People;

import java.util.List;

public class PeopleAdaptor extends RecyclerView.Adapter<PeopleAdaptor.LinearViewHolder> implements Filterable {

    private Context context;
    private List<People> peopleList;

    public PeopleAdaptor(Context context, List<People> peopleList){
        this.context = context;
        this.peopleList = peopleList;
    }


    class LinearViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_content;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = (TextView)itemView.findViewById(R.id.tv_content);
        }
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    @NonNull
    @Override
    public LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder holder, int position) {
        holder.tv_content.setText((String)peopleList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }
}

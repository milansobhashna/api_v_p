package com.example.api_v_p;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<personlist> personlist;

    public RecyclerViewAdapter(List<personlist> personlist) {
        this.personlist = personlist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listitem = layoutInflater.inflate(R.layout.list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(listitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.userid.setText(personlist.get(i).getName());
        viewHolder.id.setText(personlist.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        return personlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userid, id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userid = (TextView) itemView.findViewById(R.id.userid);
            id = (TextView) itemView.findViewById(R.id.id);


        }
    }
}

package com.example.mybeerapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybeerapp.R;
import com.example.mybeerapp.model.Beer;

import java.util.ArrayList;
import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder>{
    private List<Beer> dataList;

    public BeerAdapter(List<Beer> dataList) {
        this.dataList = dataList;
    }


    @Override
    public BeerViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_beer,parent, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder( BeerViewHolder holder, int position) {
        holder.tvId.setText(String.valueOf(dataList.get(position).getId()));
        holder.tvName.setText(dataList.get(position).getName());
        holder.tvTagline.setText(dataList.get(position).getTagline());
        holder.tvDescription.setText(dataList.get(position).getDescription());
        holder.tvFirstBrewed.setText(dataList.get(position).getFirstBrewed());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class BeerViewHolder extends RecyclerView.ViewHolder{
        TextView tvId,tvName, tvTagline,tvDescription, tvFirstBrewed;

        BeerViewHolder(View itemView){
            super(itemView);
            tvId =(TextView) itemView.findViewById(R.id.tvId);
            tvName=(TextView) itemView.findViewById(R.id.tvName);
            tvTagline=(TextView) itemView.findViewById(R.id.tvTagline);
            tvDescription=(TextView) itemView.findViewById(R.id.tvDescription);
            tvFirstBrewed=(TextView) itemView.findViewById(R.id.tvFirstBrewed);
        }
    }
}

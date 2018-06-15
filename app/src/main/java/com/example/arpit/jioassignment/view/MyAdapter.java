package com.example.arpit.jioassignment.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arpit.jioassignment.R;
import com.example.arpit.jioassignment.interfaces.IClickListener;
import com.example.arpit.jioassignment.model.DataModel;

import java.util.List;

/**
 * Created by arpit on 15/6/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<DataModel> dataList;
    private IClickListener clickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title,status;

        public ViewHolder(View itemView) {
            super(itemView);
            title  = itemView.findViewById(R.id.mytitle);
            status  = itemView.findViewById(R.id.mystatus);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(getAdapterPosition());
            }
        }
    }

    public MyAdapter(Context context, List<DataModel> data) {
        super();
        this.context = context;
        this.dataList = data;
    }
    public MyAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setClickListener(IClickListener iClickListener) {
        this.clickListener = iClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        return new MyAdapter.ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.status.setText(String.valueOf(dataList.get(position).getCompleted()));
        holder.title.setText(dataList.get(position).getTitle());

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

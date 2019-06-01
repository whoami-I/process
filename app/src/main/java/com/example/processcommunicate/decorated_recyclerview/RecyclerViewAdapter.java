package com.example.processcommunicate.decorated_recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.processcommunicate.R;
import com.example.processcommunicate.log.Log;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public RecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview,
                parent, false);


        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e("TAG", "RecyclerView.ViewHolder --> " + holder);
        RecyclerViewHolder holder1 = (RecyclerViewHolder) holder;
        holder1.tv.setText("posiition = " + position);
        Log.e("TAG", "bind view --> " + position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        public String toString() {
            String s = super.toString();
            return s + " " + getClass().getName();
        }
    }

}

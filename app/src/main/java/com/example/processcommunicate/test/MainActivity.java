package com.example.processcommunicate.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.processcommunicate.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WrapRecyclerView mRecyclerView;
    private List<Integer> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_decoratedrecyclerview);
        mItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mItems.add(i);
        }

        mRecyclerView = (WrapRecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerAdapter());
        // 添加头部 有没有问题？
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_header, mRecyclerView, false);
        View headerView2 = LayoutInflater.from(this).inflate(R.layout.item_header, mRecyclerView, false);
        View headerView3 = LayoutInflater.from(this).inflate(R.layout.item_header, mRecyclerView, false);
        mRecyclerView.addHeaderView(headerView);
        mRecyclerView.addHeaderView(headerView2);
        mRecyclerView.addHeaderView(headerView3);
        View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, mRecyclerView, false);
        mRecyclerView.addFooterView(headerView);


        Button a = findViewById(R.id.remove_head);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.removeHeaderView(headerView2);
            }
        });
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_recyclerview, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
//            if (!(holder instanceof ViewHolder)) {
//                holder = onCreateViewHolder((ViewGroup) holder.itemView.getParent(), holder.getItemViewType());
//            }
            holder.text.setText("position = " + mItems.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItems.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView text;

            public ViewHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }
}

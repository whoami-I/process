package com.example.processcommunicate.decorated_recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.processcommunicate.R;
import com.example.processcommunicate.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.remove_head)
    Button removeHead;
    @BindView(R.id.remove_foot)
    Button removeFoot;
    View header2;
    View footer2;
    WrappedRecyclerViewAdapter wrappedRecyclerViewAdapter;

    @Override
    protected void onSuccess() {
        setContentView(R.layout.layout_decoratedrecyclerview);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
        wrappedRecyclerViewAdapter =
                new WrappedRecyclerViewAdapter(recyclerViewAdapter);
        View header = LayoutInflater.from(this).inflate(R.layout.item_header, recyclerView, false);
        header2 = LayoutInflater.from(this).inflate(R.layout.item_header, recyclerView, false);
        View header3 = LayoutInflater.from(this).inflate(R.layout.item_header, recyclerView, false);
        View footer = LayoutInflater.from(this).inflate(R.layout.item_footer, recyclerView, false);
        footer2 = LayoutInflater.from(this).inflate(R.layout.item_footer, recyclerView, false);

        wrappedRecyclerViewAdapter.addHeaderView(header);
        wrappedRecyclerViewAdapter.addHeaderView(header2);
        wrappedRecyclerViewAdapter.addHeaderView(header3);
        wrappedRecyclerViewAdapter.addFooterView(footer);
        //wrappedRecyclerViewAdapter.addFooterView(footer2);

        recyclerView.setAdapter(wrappedRecyclerViewAdapter);

    }
    @OnClick(R.id.remove_head)
    public void removeHead(View v) {
        wrappedRecyclerViewAdapter.removeHeaderView(header2);
    }
    @OnClick(R.id.remove_foot)
    public void removeFoot(View v) {
        wrappedRecyclerViewAdapter.removeFooterView(footer2);
    }
}

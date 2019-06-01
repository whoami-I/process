package com.example.processcommunicate.decorated_recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.processcommunicate.log.Log;

import java.util.ArrayList;
import java.util.List;

public class WrappedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "WrappedRecyclerViewAdapter";
    private RecyclerViewAdapter mRealAdapter;
    private List<View> mHeaderView;
    private List<View> mFooterView;

    public WrappedRecyclerViewAdapter(RecyclerViewAdapter realAdapter) {
        this.mRealAdapter = realAdapter;
        mHeaderView = new ArrayList<>();
        mFooterView = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int position = viewType;
        RecyclerView.ViewHolder viewHolder = null;
        if (position < mHeaderView.size()) {
            //返回头部
            viewHolder = new WrappedViewHolder(mHeaderView.get(position)) {
            };

        } else if (position < mHeaderView.size() + mRealAdapter.getItemCount()) {
            //返回真实数据
            int adjPosition = position - mHeaderView.size();
            viewHolder = mRealAdapter.onCreateViewHolder(parent, adjPosition);
        } else {
            //返回头部
            int adjPosition = position - mHeaderView.size() - mRealAdapter.getItemCount();
            viewHolder = new WrappedViewHolder(mFooterView.get(adjPosition)) {
            };
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e(TAG, "" + holder);
        if ((position >= mHeaderView.size()) && (position < mHeaderView.size() + mRealAdapter.getItemCount())) {
            int adjPosition = position - mHeaderView.size();
            mRealAdapter.onBindViewHolder(holder, adjPosition);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //根据position来决定返回的是真实数据还是头部底部
        return position;
    }

    @Override
    public int getItemCount() {
        return mHeaderView.size() + mFooterView.size() + mRealAdapter.getItemCount();
    }

    /**
     * 添加头部
     *
     * @param view 需要添加的view对象
     */
    public void addHeaderView(View view) {
        if (!mHeaderView.contains(view)) {
            mHeaderView.add(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除头部
     *
     * @param view 删除添加的view对象
     */
    public void removeHeaderView(View view) {
        if (mHeaderView.contains(view)) {
            mHeaderView.remove(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加底部
     *
     * @param view 需要添加的view对象
     */
    public void addFooterView(View view) {
        if (!mFooterView.contains(view)) {
            mFooterView.add(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除底部
     *
     * @param view 需要删除的view对象
     */
    public void removeFooterView(View view) {
        if (mFooterView.contains(view)) {
            mFooterView.remove(view);
            notifyDataSetChanged();
        }
    }

    class WrappedViewHolder extends RecyclerView.ViewHolder {

        public WrappedViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public String toString() {
            String s = super.toString();
            return s + " haha" + getClass().getName();
        }
    }


}

package com.example.processcommunicate.viewpageradapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.processcommunicate.R;
import com.example.processcommunicate.base.BaseActivity;
import com.example.processcommunicate.log.Log;
import com.example.processcommunicate.viewpageradapter.adapter.IndicatorViewAdapter;
import com.example.processcommunicate.viewpageradapter.baseviewpager.MyViewPager;
import com.example.processcommunicate.viewpageradapter.baseviewpager.OuterAdapter;
import com.example.processcommunicate.viewpageradapter.indicator.ColorTrackTextView;
import com.example.processcommunicate.viewpageradapter.indicator.IndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.view_pager)
    MyViewPager viewPager;
    @BindView(R.id.indicator)
    IndicatorView indicatorView;
    String[] items = new String[]{"直播", "新闻", "福利", "头条", "体育新闻", "英语广播",
            "直播", "新闻", "福利", "头条", "体育新闻", "英语广播"};

    @Override
    protected void onSuccess() {
        setContentView(R.layout.layout_viewpageradapter);
        ButterKnife.bind(this);
        viewPager.setAdapter(new OuterAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                TextView textView;
                if (convertView != null) {
                    view = convertView;

                } else {
                    view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_viewpager,
                            parent, false);
                    view.setTag(new ViewHolder(view));
                }
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                viewHolder.textView.setText(items[position]);
                return view;
            }

            @Override
            public int getCount() {
                return items.length;
            }
        });

        indicatorView.setAdapter(new IndicatorViewAdapter() {
            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public View getViewAt(int i) {
                ColorTrackTextView colorTrackTextView = new ColorTrackTextView(MainActivity.this);


                colorTrackTextView.setText(items[i]);
                colorTrackTextView.setTextSize(50);
                return colorTrackTextView;
            }
        }, viewPager);

        indicatorView.setMargin(20);


    }

    static class ViewHolder {
        View convertView;
        TextView textView;

        public ViewHolder(View view) {
            convertView = view;
            textView = view.findViewById(R.id.text);
        }
    }
}

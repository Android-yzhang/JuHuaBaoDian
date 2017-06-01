package com.yzhang.juhuabaodian.Demos.WidgetModule.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yzhang.juhuabaodian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzhang on 2017/4/18.
 */

public class RecyclerViewDemoMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_demo_main_activity);

        initData();
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new HomeAdapter());
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(RecyclerViewDemoMainActivity.this, GridLayout.class));
                        break;
                    case 1:
                        startActivity(new Intent(RecyclerViewDemoMainActivity.this, StaggeredGrid.class));
                        break;
                }
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        mDatas.add("GridLayout");
        mDatas.add("瀑布流");
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
//        void onItemLongClick(View view , int position);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        private OnItemClickListener mOnItemClickListener;

        public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
            this.mOnItemClickListener = mOnItemClickListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    RecyclerViewDemoMainActivity.this).inflate(R.layout.recycler_view_main_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));

            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });

//                holder.itemView.setOnLongClickListener(new OnLongClickListener()
//                {
//                    @Override
//                    public boolean onLongClick(View v)
//                    {
//                        int pos = holder.getLayoutPosition();
//                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
//                        return false;
//                    }
//                });
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}

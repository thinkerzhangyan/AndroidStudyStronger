package com.example.androidstudystronger.recyclerview;


import android.os.Bundle;
import android.util.Log;

import com.example.androidstudystronger.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewActivity extends AppCompatActivity {

    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

//        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
//        virtualLayoutManager.setRecycleChildrenOnDetach(true);
//        RecyclerView recyclerView = null;
//        RecyclerView.RecycledViewPool recycledViewPool = null;
//        recyclerView.setRecycledViewPool(recycledViewPool);

        //getAdapterPosition和getLayoutAdapterPosition的区别的测试代码
        RecyclerView recyclerView = findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        final List<ListBean> dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add(new ListBean(String.valueOf(i)));
        }
        mListAdapter = new ListAdapter(dataList, position -> {
            Log.d("yan", "=====" + position + "====");
            dataList.add(position,new ListBean(position+""+position));
            mListAdapter.notifyDataSetChanged();
//            dataList.add(position,new ListBean(position+""+position));
//            mListAdapter.notifyItemInserted(position);
//            dataList.remove(position);
//            mListAdapter.notifyItemRemoved(position);
        });
        recyclerView.setAdapter(mListAdapter);
    }
}

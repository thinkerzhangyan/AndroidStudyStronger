package com.example.androidstudystronger.recyclerview;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidstudystronger.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.RecyclerViewHolder> {

    private List<ListBean> mDataList;

    public AdapterListener mAdapterListener;

    public ListAdapter(List<ListBean> list,AdapterListener adapterListener){
        mDataList = list;
        mAdapterListener = adapterListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int i) {
        viewHolder.bind(mDataList.get(i));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    private long mLastClickTimes = 0;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(ListBean listBean) {
            ((TextView)itemView.findViewById(R.id.text)).setText(listBean.getText());
            itemView.setOnClickListener(v -> {
                if (mAdapterListener != null) {
                    try {
                        //long startTimes = System.currentTimeMillis();
                        notifyDataSetChanged();
                        mAdapterListener.onClickItem(getLayoutPosition());
                        //Log.d("yan", "=====" + (System.currentTimeMillis() - startTimes > 16) + "=======");
                    } catch (Throwable throwable) {

                    }finally {
                        long lastTimes = System.currentTimeMillis();
                        if (mLastClickTimes != 0) {
                            Log.d("yan", "=====" + (lastTimes - mLastClickTimes>16) + "=======");
                        }
                        mLastClickTimes = lastTimes;
                    }
                }
            });
        }

    }

    public interface AdapterListener{
        void onClickItem(int position);
    }

}

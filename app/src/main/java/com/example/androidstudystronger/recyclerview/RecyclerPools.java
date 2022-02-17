package com.example.androidstudystronger.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerPools {

    private RecyclerPools() {

    }

    private static final class RecyclerPoolsHoler {
        private static final RecyclerPools RECYCLER_POOLS = new RecyclerPools();
    }

    public static RecyclerPools getInstance() {
        return RecyclerPoolsHoler.RECYCLER_POOLS;
    }

    private volatile RecyclerView.RecycledViewPool mOuterPool;

    private volatile RecyclerView.RecycledViewPool mInterPool;

    public RecyclerView.RecycledViewPool getOuterPool() {
        if (mOuterPool == null) {
            synchronized (this) {
                if (mOuterPool == null) {
                    mOuterPool = new RecyclerView.RecycledViewPool();
                }
            }
        }
        return mOuterPool;
    }

    public RecyclerView.RecycledViewPool getInterPool() {
        if (mInterPool == null) {
            synchronized (this) {
                if (mInterPool == null) {
                    mInterPool = new RecyclerView.RecycledViewPool();
                }
            }
        }
        return mInterPool;
    }

}

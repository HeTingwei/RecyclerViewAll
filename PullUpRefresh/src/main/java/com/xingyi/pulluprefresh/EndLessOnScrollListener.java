package com.xingyi.pulluprefresh;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by HeTingwei on 2017/8/27.
 */

public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {

    private static final String TAG = "EndLessOnScrollListener";

    LinearLayoutManager linearLayoutManager;

    //当前所在页
    private int currentPage=0;

    //已经加载出来的item数
    private int totalItemCount=0;

    //用来存储上一个totalItemCount
    private int previousTotal=0;

    //屏幕可见的item数量
    private int visibleItemCount;

    //屏幕可见第一个Item的位置
    private int firstVisibleItem;

    //是否上拉数据
    private boolean loading=true;

    public EndLessOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount=recyclerView.getChildCount();
        totalItemCount=linearLayoutManager.getItemCount();
        firstVisibleItem=linearLayoutManager.findFirstVisibleItemPosition();
//去掉loading也可以，但是性能会下降，在每次滑动时都会判断，所以的加上
        if(loading){
            Log.d(TAG, "firstVisibleItem: " + firstVisibleItem);
            Log.d(TAG, "totalItemCount:" + totalItemCount);
            Log.d(TAG, "visibleItemCount:" + visibleItemCount);
            Log.d(TAG, "currentPage:" + currentPage);
            if(totalItemCount>previousTotal){
                //说明数据项已经加载结束
                loading=false;
                previousTotal=totalItemCount;
            }
        }
        //实际效果是滑动到已加载页最后一项可见的瞬间，添加下一页
        if(!loading&&totalItemCount-visibleItemCount<=firstVisibleItem){
            currentPage++;
            onLoadMore(currentPage);
            loading=true;
        }

    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     * 这个方法在可见的页的最后一项，可见时调用
     * currentPage是加载到的页面编号
     */
    public abstract void onLoadMore(int currentPage);
}

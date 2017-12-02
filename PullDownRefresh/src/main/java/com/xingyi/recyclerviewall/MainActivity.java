package com.xingyi.recyclerviewall;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeTingwei on 2017/8/27.
 *
 * recyclerView向下拉，在顶端加载新子项
 */


public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    List <String>list;
    ItemAdapter adapter;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        //列表
        recyclerView= (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加数据
        list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("第"+i+"项");
        }
        adapter=new ItemAdapter(list,this);
        recyclerView.setAdapter(adapter);

        //下拉加载控件
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.srl);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);//设置旋转圈的颜色
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.add(0,"下拉加载出现的："+i++);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);//设置成true的话，下拉过后就会一直在那里转
            }
        });
    }


}

package com.xingyi.pulluprefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
/*
* recyclerView 上拉从底部刷新
* */
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> list;
    ItemAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //列表
        recyclerView= (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //添加数据
        list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("第"+i+"项");
        }
        adapter=new ItemAdapter(list,this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                for (int i = count; i < 5+count; i++) {
                    list.add("上拉加载"+i);
                }
                adapter.notifyDataSetChanged();
                count+=5;
            }
        });
    }
}

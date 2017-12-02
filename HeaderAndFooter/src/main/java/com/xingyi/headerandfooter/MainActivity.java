package com.xingyi.headerandfooter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/*
* 具有footer和Header的RecyclerView
* */

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapter adapter;
    List<String>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        list= new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("第"+i+"项");
        }
        adapter=new ItemAdapter(list,this);

        recyclerView= (RecyclerView) findViewById(R.id.rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //注意，以下两个方法必须在setAdapter()之后调用，否则长和宽会变成wrap_content
        addHeader();
        addFooter();

    }

    private void addHeader(){
        View header= LayoutInflater.from(this).inflate(R.layout.header_layout,recyclerView,false);
        adapter.setHeaderView(header);
    }

    private  void addFooter(){
        View footer= LayoutInflater.from(this).inflate(R.layout.footer_layout,recyclerView,false);
        adapter.setFooterView(footer);
    }


}

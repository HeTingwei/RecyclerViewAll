package com.xingyi.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeTingwei on 2017/8/27.
 *
 * recyclerView加载不同子项:
 * 重写适配器的getItemViewType,onCreateViewHolder和onBindViewHolder
 */


public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    List <Object>list;//元素为自定义类型，可以是不同类
    ItemAdapter adapter;

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
            if(i%3==0){
            list.add("第"+i+"项");}
            else{
                list.add(R.mipmap.ic_launcher);
            }
        }
        adapter=new ItemAdapter(list,this);
        recyclerView.setAdapter(adapter);

    }


}

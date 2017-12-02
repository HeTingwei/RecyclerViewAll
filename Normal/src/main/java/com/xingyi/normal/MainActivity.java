package com.xingyi.normal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    * 这个是普通的recyclerview，在adapter里封装了添加，去除子项，和清空recyclerView
    * */

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("" + i);
        }
        ItemAdapter itemAdapter=new ItemAdapter(list, this);
        recyclerView.setAdapter(itemAdapter);
        //itemAdapter.addItem(1,"123");
        //itemAdapter.removeItem(15);
        //itemAdapter.clearAll();
    }


}

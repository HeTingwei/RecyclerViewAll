package com.xingyi.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HeTingwei on 2017/8/27.
 */

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> list;
    Context context;
    private static final int TYPE_IMAGE = 0;
    private static final int TYPE_TEXT = 1;

    public ItemAdapter(List<Object> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        Object object=list.get(position);
        if (object instanceof String) {
            return TYPE_TEXT;
        } else if(object instanceof Integer){
            return TYPE_IMAGE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == TYPE_TEXT) {
            holder= new TextViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_text_layout, parent, false));
        }else{
            holder= new ImageViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_image_layout, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder)holder).tv.setText((String)list.get(position));
        }else if(holder instanceof ImageViewHolder){
            ((ImageViewHolder) holder).img.setImageResource((Integer) list.get(position));
            ((ImageViewHolder) holder).tv.setText(position+"");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public TextViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView img;

        public ImageViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
            img = itemView.findViewById(R.id.item_image);
        }
    }



}

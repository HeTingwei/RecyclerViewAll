package com.xingyi.headerandfooter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HeTingwei on 2017/8/27.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private List<String> list;
    private Context context;
    private View headerView;
    private View footerView;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_NORMAL = 2;


    public ItemAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(headerView);
        }
        if (footerView != null && viewType == TYPE_FOOTER) {
            return new MyViewHolder(footerView);
        }

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            holder.tv.setText(list.get(position - 1));
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else
            return;
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view
     */
    @Override
    public int getItemViewType(int position) {
        if (headerView == null && footerView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1) {
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (headerView == null && footerView == null) {
            return list.size();
        } else if (headerView == null && footerView != null) {
            return list.size() + 1;
        } else if (headerView != null && footerView == null) {
            return list.size() + 1;
        } else {
            return list.size() + 2;
        }
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView=headerView;
        notifyItemInserted(0);
    }

    public View getFooterView() {
        return footerView;
    }

    public void setFooterView(View footerView) {
        this.footerView=footerView;
        notifyItemInserted(getItemCount()-1);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}

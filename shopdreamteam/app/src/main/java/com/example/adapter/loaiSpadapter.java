package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.model.Loaisp;
import com.example.shopdreamteam.R;

import java.util.List;

public class loaiSpadapter extends BaseAdapter {
    List<Loaisp> array;
    Context context;

    public loaiSpadapter( Context context,List<Loaisp> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class  ViewHolder{
        TextView texttensp;
        ImageView imgsp;

    }

    @Override
    public View getView(int i,View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null ) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item, null);
            viewHolder.texttensp = view.findViewById(R.id.textitem);
            viewHolder.imgsp = view.findViewById(R.id.item_pro);
            view.setTag(viewHolder);
        }
        else {
                viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.texttensp.setText(array.get(i).getTensanpham());
        Glide.with(context).load(array.get(i).getHinhanh()).into(viewHolder.imgsp);
        return view;


    }
}

package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.model.productModel;
import com.example.shopdreamteam.R;

import java.text.DecimalFormat;
import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.MyviewHolder> {
    Context context;
    List<productModel> array;

    public productAdapter(Context context, List<productModel> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);

        return new MyviewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        productModel productModel = array.get(position);
        holder.txtten.setText(productModel.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: " + decimalFormat.format(Double.parseDouble(productModel.getGia()))+ "Đ");
        Glide.with(context).load(productModel.getHinhanh()).into(holder.imghinhanh);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView txtgia, txtten;
        ImageView imghinhanh;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            txtgia = itemView.findViewById(R.id.itemSP_gia);
            txtten = itemView.findViewById(R.id.itemSP_tensp);
            imghinhanh = itemView.findViewById(R.id.itemSP_img);
        }
    }
}

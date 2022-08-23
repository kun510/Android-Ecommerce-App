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

public class phoneAdapter extends RecyclerView.Adapter<phoneAdapter.MyViewHolder> {
    Context context;
    List<productModel> array;

    public phoneAdapter(Context context, List<productModel> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        productModel sp =  array.get(position);
        holder.tensp.setText(sp.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá: " + decimalFormat.format(Double.parseDouble(sp.getGia()))+ "Đ");
        holder.soluong.setText(sp.getSoluong());
        holder.mota.setText(sp.getMota());
        Glide.with(context).load(sp.getHinhanh()).into(holder.hinhanh);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tensp,giasp,soluong,mota;
        ImageView hinhanh;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tensp = itemView.findViewById(R.id.item_ten);
            giasp = itemView.findViewById(R.id.itemdt_gia);
            soluong = itemView.findViewById(R.id.itemdt_SL);
            mota = itemView.findViewById(R.id.itemdt_mota);
            hinhanh = itemView.findViewById(R.id.itemPhone_img);
        }
    }
}

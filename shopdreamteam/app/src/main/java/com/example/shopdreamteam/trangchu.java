package com.example.shopdreamteam;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adapter.loaiSpadapter;
import com.example.adapter.productAdapter;
import com.example.model.Loaisp;
import com.example.model.productModel;
import com.example.retrofit.APiBanhang;
import com.example.urlits.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.example.retrofit.retrofitClient;
public class trangchu extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewflip;
    RecyclerView recyclerView;
    NavigationView navigation;
    ListView listview;
    DrawerLayout drawerLayout;
    loaiSpadapter loaiSpadapter;
    List<Loaisp> mangloaisp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APiBanhang aPiBanhang;
    List<productModel> mangsanpham;
    productAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        aPiBanhang = retrofitClient.getInstance(Utils.BASE_URl).create(APiBanhang.class);

        anhxa();
        actionBar();
        ActionViewFlipper();
        if (isConnectTed(this)){
            ActionViewFlipper();
            getLoaisanpham();
            getSpmoi();
            getEvenlistmenu();
        }
        else {
            Toast.makeText(getApplicationContext(), "No Internet,Please Connect", Toast.LENGTH_SHORT).show();
        }
    }

    private void getEvenlistmenu() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent trangchu = new Intent(getApplicationContext(),trangchu.class);
                        startActivity(trangchu);
                    break;
                    case 1:
                        Intent laptop = new Intent(getApplicationContext(),laptop.class);
                        startActivity(laptop);
                        break;

                    case 2:
                        Intent dienthoai = new Intent(getApplicationContext(),dienthoai.class);
                        dienthoai.putExtra("loai",1);
                        startActivity(dienthoai);
                        break;
                }
            }
        });
    }

    private void getSpmoi() {
        compositeDisposable.add(aPiBanhang.getSpmoi()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                productmodel2 -> {
                    if (productmodel2.isSucces()){
                        mangsanpham = productmodel2.getResult();
                        productAdapter = new productAdapter(getApplicationContext(),mangsanpham);
                        recyclerView.setAdapter(productAdapter);
                    }
                },
                throwable -> {
                    Toast.makeText(getApplicationContext(),"No connect with sever, Please"+throwable.getMessage(),Toast.LENGTH_SHORT).show();
                }
        ));

    }

    private void getLoaisanpham() {
        compositeDisposable.add(aPiBanhang.getLoaiSp()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                loaispModel -> {
//                    Log.d("tettttt", loaispModel.getResult().size() + "  ");
                    if(loaispModel.isSucces()){
                       mangloaisp = loaispModel.getResult();
                        // khoi tao adapter
                        loaiSpadapter = new loaiSpadapter(getApplicationContext(),mangloaisp);
                        listview.setAdapter(loaiSpadapter);
                    }
                }
        ));
    }

    private void ActionViewFlipper(){
        List<String> mauquangcao = new  ArrayList<>();
        mauquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mauquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mauquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for (int i=0; i<mauquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mauquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewflip
                    .addView(imageView);

        }
        viewflip.setFlipInterval(3000);
        viewflip.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_anh_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_anh_out_right);
        viewflip.setInAnimation(slide_in);
        viewflip.setOutAnimation(slide_out);
    }
    private void actionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void  anhxa(){
        toolbar = findViewById(R.id.titlehome);
        viewflip = findViewById(R.id.viewflip);
        recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        navigation = findViewById(R.id.navigation);
        listview = findViewById(R.id.listview);
        drawerLayout = findViewById(R.id.drawerlayout);
        //khoi tao list
        mangloaisp = new ArrayList<>();
        mangsanpham = new ArrayList<>();

    }
    //ham kiem tra internet
    private  boolean isConnectTed(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI);
        NetworkInfo moblile = connectivityManager.getNetworkInfo(connectivityManager.TYPE_MOBILE);
        if( (wifi != null && wifi.isConnected())||(moblile != null & moblile.isConnected()) ){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
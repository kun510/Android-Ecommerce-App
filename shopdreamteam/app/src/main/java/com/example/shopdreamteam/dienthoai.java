package com.example.shopdreamteam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.phoneAdapter;
import com.example.adapter.productAdapter;
import com.example.model.productModel;
import com.example.retrofit.APiBanhang;
import com.example.retrofit.retrofitClient;
import com.example.urlits.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class dienthoai extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    APiBanhang aPiBanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page = 1;
    int loai;
    phoneAdapter phoneAdapter;
    List<productModel> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dienthoai);
        aPiBanhang = retrofitClient.getInstance(Utils.BASE_URl).create(APiBanhang.class);
        loai = getIntent().getIntExtra("loai",1);
        Anhxa();
        ActionToolBar();
        getDATA();
    }

    private void getDATA() {
        compositeDisposable.add(aPiBanhang.getsp(page,loai)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                productmodel2 -> {
                    Log.d("tettttt", productmodel2.getResult().size() + "  ");
                    if (productmodel2.isSucces()){
                        productList = productmodel2.getResult();
                        phoneAdapter = new phoneAdapter(getApplicationContext(),productList);
                        recyclerView.setAdapter(phoneAdapter);
                    }


                },
                throwable -> {Toast.makeText(getApplicationContext(),"No connect with sever, Please"+throwable.getMessage(),Toast.LENGTH_SHORT).show();}
        ));

    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toobar);
        recyclerView = findViewById(R.id.recyclerview_Phone);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        productList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
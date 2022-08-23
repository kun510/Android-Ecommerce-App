package com.example.retrofit;

import com.example.model.loaispModel;
import com.example.model.productmodel2;

import io.reactivex.rxjava3.core.Observable;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APiBanhang {
    @GET("listviewmenu.php")
    Observable<loaispModel> getLoaiSp();

    @GET("product.php")
    Observable<productmodel2> getSpmoi();


   @POST("chitiettungsp.php")
   @FormUrlEncoded
   Observable<productmodel2> getsp(
           @Field("page") int page,
           @Field("loai") int loai
   );
}

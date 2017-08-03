package com.samapps.receiptpuppie.model.api;

import com.samapps.receiptpuppie.model.dto.UserModel;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {

    @GET("api/")
    Observable<UserModel> getReceipts ();

}

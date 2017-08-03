package com.samapps.receiptpuppie.model;

import com.samapps.receiptpuppie.model.dto.UserModel;

import rx.Observable;

public interface Model {

    Observable<UserModel> searchReceipts ();



}

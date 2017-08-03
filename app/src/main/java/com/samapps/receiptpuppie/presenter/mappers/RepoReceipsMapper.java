package com.samapps.receiptpuppie.presenter.mappers;

import com.samapps.receiptpuppie.model.dto.Result;
import com.samapps.receiptpuppie.model.dto.UserModel;

import java.util.List;

import javax.inject.Inject;

import io.realm.OrderedRealmCollection;
import rx.Observable;
import rx.functions.Func1;

public class RepoReceipsMapper implements Func1<List<UserModel>, List<Result>> {

    @Inject
    public RepoReceipsMapper() {
    }

    @Override
    public OrderedRealmCollection<Result> call(List<UserModel> userModels) {
        if(userModels==null){
            return null;
        }
        Observable<List<Result>> receips = Observable.from(userModels).map(userModel -> new Result()).toList();
        return (OrderedRealmCollection<Result>) receips;
    }
}

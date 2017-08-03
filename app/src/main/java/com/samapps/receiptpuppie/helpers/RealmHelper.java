package com.samapps.receiptpuppie.helpers;

import com.samapps.receiptpuppie.model.dto.Result;

import java.util.List;

import io.realm.RealmObject;

import static com.samapps.receiptpuppie.view.SplashActivity.realm;

/**
 * Created by breez_000 on 8/3/2017.
 */

public class RealmHelper implements IRealHelper {


    @Override
    public void copyToRealmOrUpdate(RealmObject realmObject) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObject);
        realm.commitTransaction();
    }

    @Override
    public void copyListRealmOrUpdate(List<Result> realmObjects) {
        for(Result obj:realmObjects){
            copyToRealmOrUpdate(obj);
        }
    }
}

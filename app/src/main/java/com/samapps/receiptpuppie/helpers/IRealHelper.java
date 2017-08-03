package com.samapps.receiptpuppie.helpers;

import com.samapps.receiptpuppie.model.dto.Result;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by breez_000 on 8/3/2017.
 */

public interface IRealHelper {

    void copyToRealmOrUpdate(RealmObject realmObject);

    void copyListRealmOrUpdate(List<Result> realmObjects);
}

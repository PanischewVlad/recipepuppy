package com.samapps.receiptpuppie.view;

import com.samapps.receiptpuppie.model.dto.Result;

/**
 * Created by breez_000 on 8/2/2017.
 */

public interface ActivityCallback {
    void startRepoInfoFragment(Result repository);

    void showProgressBar();

    void hideProgressBar();
}

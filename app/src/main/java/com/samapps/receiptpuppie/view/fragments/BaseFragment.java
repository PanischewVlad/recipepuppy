package com.samapps.receiptpuppie.view.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.samapps.receiptpuppie.view.ActivityCallback;
import com.samapps.receiptpuppie.presenter.Presenter;

public abstract class BaseFragment extends Fragment implements View {

    protected ActivityCallback activityCallback;

    protected abstract Presenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }
    @Override
    public void showLoading() {
        activityCallback.showProgressBar();
    }

    @Override
    public void hideLoading() {
        activityCallback.hideProgressBar();
    }
}


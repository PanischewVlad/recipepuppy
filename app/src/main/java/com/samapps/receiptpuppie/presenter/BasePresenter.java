package com.samapps.receiptpuppie.presenter;

import android.support.v7.app.AppCompatActivity;

import com.samapps.receiptpuppie.model.Model;
import com.samapps.receiptpuppie.other.App;
import com.samapps.receiptpuppie.view.fragments.View;

import javax.inject.Inject;

import dagger.Module;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
@Module
public abstract class BasePresenter extends AppCompatActivity implements Presenter {

    @Inject
    protected Model model;

    @Inject
    protected CompositeSubscription compositeSubscription;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

    protected abstract View getView();

    protected void showLoadingState() {
        getView().showLoading();
    }

    protected void hideLoadingState() {
        getView().hideLoading();
    }

    protected void showError(Throwable e) {
        getView().showError(e.getMessage());
    }


}

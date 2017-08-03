package com.samapps.receiptpuppie.model;

import com.samapps.receiptpuppie.model.api.ApiInterface;
import com.samapps.receiptpuppie.model.dto.UserModel;
import com.samapps.receiptpuppie.other.App;
import com.samapps.receiptpuppie.other.Const;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    protected ApiInterface apiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
        schedulersTransformer = o -> ((Observable) o).subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread); // TODO: remove when https://github.com/square/okhttp/issues/1592 is fixed
    }

    @Override
    public Observable<UserModel> searchReceipts() {
        return apiInterface
                .getReceipts().compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}

package com.samapps.receiptpuppie.other.di;

import com.samapps.receiptpuppie.model.ModelImpl;
import com.samapps.receiptpuppie.presenter.BasePresenter;
import com.samapps.receiptpuppie.presenter.RepoListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);

    void inject(RepoListPresenter repoListPresenter);

}

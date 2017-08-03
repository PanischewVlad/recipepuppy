package com.samapps.receiptpuppie.other.di.view;

import com.samapps.receiptpuppie.presenter.RepoListPresenter;
import com.samapps.receiptpuppie.view.fragments.RepoListView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {

    private RepoListView view;

    public ViewDynamicModule(RepoListView view) {
        this.view = view;
    }

    @Provides
    RepoListPresenter provideRepoListPresenter() {
        return new RepoListPresenter(view);
    }

}

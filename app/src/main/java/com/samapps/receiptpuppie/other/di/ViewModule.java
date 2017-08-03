package com.samapps.receiptpuppie.other.di;

import com.samapps.receiptpuppie.presenter.RepoListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {
    @Provides
    RepoListPresenter provideRepoListPresenter(){
        return new RepoListPresenter();
    }


}

package com.samapps.receiptpuppie.other.di.view;

import com.samapps.receiptpuppie.view.fragments.RepoListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {

    void inject(RepoListFragment repoListFragment);

}

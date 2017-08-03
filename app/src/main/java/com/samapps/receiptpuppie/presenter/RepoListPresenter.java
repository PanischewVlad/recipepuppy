package com.samapps.receiptpuppie.presenter;

import android.os.Bundle;

import com.samapps.receiptpuppie.model.dto.Result;
import com.samapps.receiptpuppie.other.App;
import com.samapps.receiptpuppie.presenter.mappers.RepoReceipsMapper;
import com.samapps.receiptpuppie.view.fragments.RepoListView;
import com.samapps.receiptpuppie.view.fragments.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RepoListPresenter extends BasePresenter {

    private static final String BUNDLE_REPO_LIST_KEY = "BUNDLE_REPO_LIST_KEY";
    @Inject
    protected RepoReceipsMapper receipsMapper;

    private RepoListView view;

    private List<Result> repoList;

    // for DI
    @Inject
    public RepoListPresenter() {
    }

    public RepoListPresenter(RepoListView view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    @Override
    public View getView() {
        return view;
    }



    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            repoList = (List<Result>) savedInstanceState.getSerializable(BUNDLE_REPO_LIST_KEY);
        if (isRepoListNotEmpty()) view.showRepoList(repoList);
    }

    private boolean isRepoListNotEmpty() {
        return (repoList != null && !repoList.isEmpty());
    }

    public void onSaveInstanceState(Bundle outState) {
        if (isRepoListNotEmpty()) {
            outState.putSerializable(BUNDLE_REPO_LIST_KEY, new ArrayList<>(repoList));
        }
    }


}

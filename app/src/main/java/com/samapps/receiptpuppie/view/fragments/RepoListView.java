package com.samapps.receiptpuppie.view.fragments;

import com.samapps.receiptpuppie.model.dto.Result;

import java.util.List;

public interface RepoListView extends View {

    void showRepoList(List<Result> userModels);

    void showEmptyList();

    String getTextField();

}

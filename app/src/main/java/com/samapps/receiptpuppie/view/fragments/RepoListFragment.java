package com.samapps.receiptpuppie.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samapps.receiptpuppie.R;
import com.samapps.receiptpuppie.model.dto.Result;
import com.samapps.receiptpuppie.other.di.view.DaggerViewComponent;
import com.samapps.receiptpuppie.other.di.view.ViewComponent;
import com.samapps.receiptpuppie.other.di.view.ViewDynamicModule;
import com.samapps.receiptpuppie.presenter.BasePresenter;
import com.samapps.receiptpuppie.presenter.RepoListPresenter;
import com.samapps.receiptpuppie.view.adapters.RepoListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.samapps.receiptpuppie.view.SplashActivity.realm;

public class RepoListFragment extends BaseFragment implements RepoListView {
    private List<Result> results;

    @Bind(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @Bind(R.id.edit_text)
    protected SearchView editText;



    @Inject
    protected RepoListPresenter presenter;
    private RepoListAdapter adapter;
    private ViewComponent viewComponent;
    public List<Result> result;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        results = realm.where(Result.class).isNotNull("href").findAll();
        result = new ArrayList<Result>();
        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        viewComponent.inject(this);
        super.onCreate(savedInstanceState);
    }

    public void setViewComponent(ViewComponent viewComponent) {
        this.viewComponent = viewComponent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);


        presenter.onCreateView(savedInstanceState);
        if(editText!=null){

            editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    final List<Result> filteredModelList = filter(results, newText);
                    adapter.setFilter(filteredModelList);
                    return true;
                }
            });
        }
        adapter = new RepoListAdapter(results, presenter,getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void makeToast(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showRepoList(List<Result> userModels) {
        adapter.setRepoList(userModels);
    }

    @Override
    public void showEmptyList() {
        makeToast("Empty List");
    }

    @Override
    public String getTextField() {
        return editText.toString();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    private List<Result> filter(List<Result> models, String query) {
        query = query.toLowerCase();
        final List<Result> filteredModelList = new ArrayList<>();
        for (Result receipt : models) {
            final String text = receipt.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(receipt);
            }
        }
        return filteredModelList;
    }

}

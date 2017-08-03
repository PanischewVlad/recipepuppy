package com.samapps.receiptpuppie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.samapps.receiptpuppie.model.dto.Result;
import com.samapps.receiptpuppie.view.ActivityCallback;
import com.samapps.receiptpuppie.view.fragments.RepoListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.samapps.receiptpuppie.view.SplashActivity.realm;

public class MainActivity extends AppCompatActivity implements ActivityCallback {
    private static String TAG = "TAG";


    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.toolbar_progress_bar)
    protected ProgressBar progressBar;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) replaceFragment(new RepoListFragment(), false);
    }
    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void startRepoInfoFragment(Result repository) {

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}

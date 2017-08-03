package com.samapps.receiptpuppie.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.samapps.receiptpuppie.MainActivity;
import com.samapps.receiptpuppie.R;
import com.samapps.receiptpuppie.helpers.RealmHelper;
import com.samapps.receiptpuppie.model.Model;
import com.samapps.receiptpuppie.model.ModelImpl;
import com.samapps.receiptpuppie.model.dto.UserModel;

import io.realm.Realm;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class SplashActivity extends AppCompatActivity {
    final private int SPLASH_DISPLAY_LENGTH=2000;
    public static Realm realm;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        ImageView logo = (ImageView)findViewById(R.id.logo);
        hideSystemUI();
        setImage(logo);
        openMain();
    }

    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void openMain(){
        loadData();
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void loadData() {
        Model model = new ModelImpl();

        Subscription sub = model.searchReceipts().subscribe(new Observer<UserModel>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_SHORT);
            }

            @Override
            public void onNext(UserModel result) {
                if (result.getResults() != null && !result.getResults().isEmpty()) {
                    result.getResults();
                    new RealmHelper().copyListRealmOrUpdate(result.getResults());
                } else {
                    Toast.makeText(getBaseContext(),"No internet connection",Toast.LENGTH_SHORT);
                }
            }
        });
        new CompositeSubscription().add(sub);
    }

    private void setImage( ImageView img){
        Glide.with(this).load(R.drawable.requestum)
                .thumbnail(0.5f)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(this))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(img);
    }


}

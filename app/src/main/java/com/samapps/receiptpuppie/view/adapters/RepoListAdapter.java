package com.samapps.receiptpuppie.view.adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.samapps.receiptpuppie.model.dto.Result;
import com.samapps.receiptpuppie.presenter.RepoListPresenter;

import java.util.List;

import io.realm.RealmList;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class RepoListAdapter extends BaseAdapter<Result> {

    private RepoListPresenter presenter;
    private Context context;

    public RepoListAdapter(List<Result> list, RepoListPresenter presenter, Context context) {
        super(list,context);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        Result repo = list.get(i);
        viewHolder.text.setText(repo.getTitle());
        viewHolder.ingredients.setText(repo.getIngredients());
        setImage(repo, viewHolder.thumbnail);
        viewHolder.cv.setOnClickListener(v ->
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(repo.getHref()))));
    }

    private void setImage(Result repo, ImageView img){
        Glide.with(context).load(repo.getThumbnail())
                .thumbnail(0.5f)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(context))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(img);
    }

    public void setRepoList(List<Result> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setFilter(List<Result> msg) {
        list = new RealmList<>();
        list.addAll(msg);
        notifyDataSetChanged();
    }
}

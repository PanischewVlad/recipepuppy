package com.samapps.receiptpuppie.view.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samapps.receiptpuppie.R;
import com.samapps.receiptpuppie.model.dto.Result;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    protected List<Result> list;
    private Context context;

    public BaseAdapter(List<Result> orderedRealmCollection, Context context) {
        list=orderedRealmCollection;
        this.context=context;
    }

    public List<Result> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.receipt_card, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView text,ingredients;
        CardView cv;
        ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            text = (TextView) itemView.findViewById(R.id.title);
            ingredients = (TextView) itemView.findViewById(R.id.ingredients);
            thumbnail=(ImageView)itemView.findViewById(R.id.thumbnail);
        }
    }

}

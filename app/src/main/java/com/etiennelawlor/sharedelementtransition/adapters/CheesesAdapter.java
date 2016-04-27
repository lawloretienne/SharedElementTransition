package com.etiennelawlor.sharedelementtransition.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.etiennelawlor.sharedelementtransition.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by etiennelawlor on 4/27/16.
 */
public class CheesesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // region Constants
    private static final int ITEM = 1;
    // endregion

    // region Member Variables
    private List<Integer> cheeses;
    private OnItemClickListener onItemClickListener;
    // endregion

    // region Interfaces
    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }
    // endregion

    // region Constructors
    public CheesesAdapter() {
        cheeses = new ArrayList<>();
    }
    // endregion

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case ITEM:
                viewHolder = createCheeseViewHolder(parent);
                break;
            default:
                Log.e("", String.format("[ERR] type is not supported!!! type is %d", viewType));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case ITEM:
                bindCheeseViewHolder(viewHolder, position);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cheeses.size();
    }

    @Override
    public int getItemViewType(int position) {
//        return (position == venues.size()-1 && isLoadingFooterAdded) ? LOADING : ITEM;
        return ITEM;
    }

    // region Helper Methods

    private RecyclerView.ViewHolder createCheeseViewHolder(ViewGroup parent) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cheese_row, parent, false);

        final CheeseViewHolder holder = new CheeseViewHolder(v);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = holder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(adapterPos, holder.itemView);
                    }
                }
            }
        });

        return holder;
    }

    private void bindCheeseViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final CheeseViewHolder holder = (CheeseViewHolder) viewHolder;

        final int cheese = cheeses.get(position);
        setUpImage(holder.imageView, cheese);
    }

    private void add(int item) {
        cheeses.add(item);
        notifyItemInserted(cheeses.size() - 1);
    }

    public void addAll(List<Integer> cheeses) {
        for (Integer cheese : cheeses) {
            add(cheese);
        }
    }

    public void remove(Integer item) {
        int position = cheeses.indexOf(item);
        if (position > -1) {
            cheeses.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Integer getItem(int position) {
        return cheeses.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void setUpImage(ImageView iv, int cheese){

        int cheeseDrawable = 0;
        switch (cheese){
            case 0:
                cheeseDrawable = R.drawable.cheese_1;
                break;
            case 1:
                cheeseDrawable = R.drawable.cheese_2;
                break;
            case 2:
                cheeseDrawable = R.drawable.cheese_3;
                break;
            case 3:
                cheeseDrawable = R.drawable.cheese_4;
                break;
            case 4:
                cheeseDrawable = R.drawable.cheese_5;
                break;
        }

        Picasso.with(iv.getContext())
                .load(cheeseDrawable)
                .into(iv);
    }

    // endregion

    // region Inner Classes

    public static class CheeseViewHolder extends RecyclerView.ViewHolder {
        // region Views
        @Bind(R.id.iv)
        ImageView imageView;
        // endregion

        // region Constructors
        public CheeseViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        // endregion
    }

    // endregion
}
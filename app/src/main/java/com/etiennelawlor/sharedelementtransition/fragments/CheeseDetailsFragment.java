package com.etiennelawlor.sharedelementtransition.fragments;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.etiennelawlor.sharedelementtransition.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by etiennelawlor on 4/27/16.
 */
public class CheeseDetailsFragment extends Fragment {


    // region Views
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.appbar)
    AppBarLayout appBarLayout;
    @Bind(R.id.iv)
    ImageView imageView;
    // endregion

    // region Member Variables
    private ActionBar actionBar;
    private Integer cheese;
    // endregion

    // region Constructors
    public CheeseDetailsFragment() {
    }
    // endregion

    // region Factory Methods
    public static CheeseDetailsFragment newInstance() {
        return new CheeseDetailsFragment();
    }

    public static CheeseDetailsFragment newInstance(Bundle extras) {
        CheeseDetailsFragment fragment = new CheeseDetailsFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion

    // region Lifecycle Methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            cheese = getArguments().getInt("cheese");
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cheese_details, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            setCollapsingToolbarTitle("");
        }

        setUpImage(imageView, cheese);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    // endregion

    // region Helper Methods
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

    private void setCollapsingToolbarTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            collapsingToolbarLayout.setTitle(title);
        } else {
            collapsingToolbarLayout.setTitle("");
        }
    }

    // endregion

}
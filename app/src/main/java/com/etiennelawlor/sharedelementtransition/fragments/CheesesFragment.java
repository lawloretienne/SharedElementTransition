package com.etiennelawlor.sharedelementtransition.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etiennelawlor.sharedelementtransition.R;
import com.etiennelawlor.sharedelementtransition.activities.CheeseDetailsActivity;
import com.etiennelawlor.sharedelementtransition.adapters.CheesesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by etiennelawlor on 4/27/16.
 */
public class CheesesFragment extends Fragment implements CheesesAdapter.OnItemClickListener {

    // region Views
    @Bind(R.id.rv)
    RecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    // endregion

    // region Member Variables
    private CheesesAdapter cheesesAdapter;
    private LinearLayoutManager layoutManager;
    private boolean upNav;
    // endregion

    // region Constructors
    public CheesesFragment() {
    }
    // endregion

    // region Factory Methods
    public static CheesesFragment newInstance() {
        return new CheesesFragment();
    }

    public static CheesesFragment newInstance(Bundle extras) {
        CheesesFragment fragment = new CheesesFragment();
        fragment.setArguments(extras);
        return fragment;
    }
    // endregion

    // region Lifecycle Methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            upNav = getArguments().getBoolean("up_nav");
        }

        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cheeses, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(getString(R.string.cheeses));

            if(!upNav){
                ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            }
        }

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        cheesesAdapter = new CheesesAdapter();
        cheesesAdapter.setOnItemClickListener(this);

//        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setAdapter(cheesesAdapter);

        List<Integer> cheeses = new ArrayList<>();
        cheeses.add(0);
        cheeses.add(1);
        cheeses.add(2);
        cheeses.add(3);
        cheeses.add(4);

        cheesesAdapter.addAll(cheeses);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    // endregion

    // region CheesesAdapter.OnItemClickListener Methods
    @Override
    public void onItemClick(int position, View view) {
        Integer cheese = cheesesAdapter.getItem(position);


        Intent intent = new Intent(getActivity(), CheeseDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("cheese", cheese);
        intent.putExtras(bundle);

        View venueThumbnailImageView = view.findViewById(R.id.iv);
        Resources resources = view.getResources();

        Pair<View, String> p1 = Pair.create(venueThumbnailImageView, resources.getString(R.string.transition_cheese_thumbnail));

        View decor = getActivity().getWindow().getDecorView();
        View statusBar = decor.findViewById(android.R.id.statusBarBackground);
        View navBar = decor.findViewById(android.R.id.navigationBarBackground);
//        View actionBar = decor.findViewById(getResources().getIdentifier(
//                "action_bar_container", "id", "android"));

        Pair<View, String> p2 = Pair.create(statusBar, resources.getString(R.string.transition_status_bar));
        Pair<View, String> p3 = Pair.create(navBar, resources.getString(R.string.transition_nav_bar));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                p1, p2, p3);

        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
////            startActivity(intent);

    }
    // endregion
}

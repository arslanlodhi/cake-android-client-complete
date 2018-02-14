package com.waracle.androidtest.views;

/**
 * Created by arslanlodhi on 2/13/18.
 */

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waracle.androidtest.R;
import com.waracle.androidtest.adapters.CakeListAdapter;
import com.waracle.androidtest.base.BaseFragment;
import com.waracle.androidtest.databinding.FragmentMainBinding;
import com.waracle.androidtest.enums.ViewModelEventsEnum;
import com.waracle.androidtest.models.CakeModel;
import com.waracle.androidtest.viewmodels.CakeListFragmentViewModel;

import java.util.ArrayList;


/**
 * Fragment is responsible for loading in some JSON and
 * then displaying a list of cakes with images.
 * Fix any crashes
 * Improve any performance issues
 * Use good coding practices to make code more secure
 */
public class CakeListFragment extends BaseFragment<CakeListFragmentViewModel,FragmentMainBinding> {

    private static final String TAG = CakeListFragment.class.getSimpleName();
    public final static String LIST_STATE_KEY = "recycler_list_state";

    RecyclerView recyclerView;
    CakeListAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
    Parcelable listState;


    public CakeListFragment() { /**/ }



    @Override
    public Class<CakeListFragmentViewModel> getViewModel() {
        return CakeListFragmentViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public void onObserve(ViewModelEventsEnum event, Object eventMessage) {
    Log.e("Here"+eventMessage,""+event);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        recyclerView=rootView.findViewById(R.id.rc_view);
        setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create and set the list adapter.
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new CakeListAdapter(getContext(),viewModel);
        recyclerView.setAdapter(mAdapter);
        // Load data from net.
        viewModel.processServerRequest().observe(this, new Observer<ArrayList<CakeModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CakeModel> cakeModels) {
                mAdapter.setItems(cakeModels);
            }
        });

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        listState = layoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (listState != null) {
            layoutManager.onRestoreInstanceState(listState);
        }
    }


}
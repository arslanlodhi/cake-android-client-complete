package com.waracle.androidtest.adapters;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.waracle.androidtest.R;
import com.waracle.androidtest.databinding.ListItemLayoutBinding;
import com.waracle.androidtest.models.CakeModel;
import com.waracle.androidtest.viewmodels.CakeListFragmentViewModel;

import java.util.ArrayList;


public class CakeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Can you think of a better way to represent these items???
    //?? Created the Model class to represent Cake Entity
    //?? Used RecyclerView to implement ViewHolder patten for re usability

    private ArrayList<CakeModel> mItems;


    ViewModel viewModel;

    LayoutInflater inflater;
    Context context;

    public CakeListAdapter(Context context,ViewModel viewModel)
    {
        this(new ArrayList<CakeModel>(),viewModel,context);
    }

    public CakeListAdapter(ArrayList<CakeModel> items,ViewModel viewModel,Context context) {
        mItems = items;
        this.context= context;
        this.viewModel=viewModel;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public CakeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ListItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.list_item_layout,
                parent, false);

        return new CakeListViewHolder(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CakeListViewHolder)holder).binding.setCakeModel(mItems.get(position));
        ((CakeListFragmentViewModel)viewModel).loadImage(mItems.get(position).getImage(), ((CakeListViewHolder)holder).image);

    }



    public void setItems(ArrayList<CakeModel> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public class CakeListViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        ListItemLayoutBinding binding;

        public CakeListViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);

        }

    }
}

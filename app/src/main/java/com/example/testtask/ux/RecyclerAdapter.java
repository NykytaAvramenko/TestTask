package com.example.testtask.ux;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testtask.R;
import com.example.testtask.model.ListItemReadableInterface;


import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ListItemViewHolder> {


    private List<ListItemReadableInterface> mAllItems;

    public RecyclerAdapter(List<ListItemReadableInterface> allItems) {
        mAllItems = allItems;
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageViewIcon;
        private TextView mTextViewDescription;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            mImageViewIcon = itemView.findViewById(R.id.imageViewIcon);
            mTextViewDescription = itemView.findViewById(R.id.textViewDescription);
        }

        public void loadToImageViewFromUrl(String url) {
            Glide.with(itemView)
                    .load(url)
                    .error(R.drawable.on_error_placeholder_00)
                    .into(mImageViewIcon);
        }

        public void setDescriptionText(String description) {
            mTextViewDescription.setText(description);
        }
    }


    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_for_recycler, parent, false);
        ListItemViewHolder listItemViewHolder = new ListItemViewHolder(view);
        return listItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        String iconUrl = mAllItems.get(position).getIconUrl();
        String description = mAllItems.get(position).getDescription();
        holder.loadToImageViewFromUrl(iconUrl);
        holder.setDescriptionText(description);
    }

    @Override
    public int getItemCount() {
        return mAllItems.size();
    }

    public void swapList(List<ListItemReadableInterface> newList) {
        mAllItems = newList;
        notifyDataSetChanged();
    }


}
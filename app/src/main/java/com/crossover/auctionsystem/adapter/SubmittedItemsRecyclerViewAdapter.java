package com.crossover.auctionsystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.activities.ViewAllBidsOnItemActivity;
import com.crossover.auctionsystem.model.Item;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by suraj on 24/9/16.
 */

public class SubmittedItemsRecyclerViewAdapter extends RecyclerView.Adapter<SubmittedItemsRecyclerViewAdapter.ItemViewHolder> {

    private Context mContext;
    private ArrayList<Item> items;

    private static final int ITEM_SOLD_VIEW_TYPE = 0;
    private static final int ITEM_NOT_SOLD_VIEW_TYPE = 1;

    public SubmittedItemsRecyclerViewAdapter(Context context, ArrayList<Item> items) {
        this.mContext = context;
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layuotResId;

        if(viewType == ITEM_SOLD_VIEW_TYPE) {
            layuotResId = R.layout.row_submitted_items_sold;
        } else {
            layuotResId = R.layout.row_submitted_items_not_sold;
        }

        View itemView = LayoutInflater.from(mContext).inflate(layuotResId, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = items.get(position);

        holder.setItem(item);

        holder.nameTextView.setText(item.getItemName());
        holder.descriptionTextView.setText(item.getItemDescription());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTextView;
        private TextView descriptionTextView;
        private Item item;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.item_name_textview);
            descriptionTextView = (TextView) itemView.findViewById(R.id.item_description_textview);
        }

        @Override
        public void onClick(View view) {
            EventBus.getDefault().postSticky(item);

            Intent intent = new Intent(mContext, ViewAllBidsOnItemActivity.class);
            mContext.startActivity(intent);
        }

        public void setItem(Item item) {
            this.item = item;
        }
    }
}

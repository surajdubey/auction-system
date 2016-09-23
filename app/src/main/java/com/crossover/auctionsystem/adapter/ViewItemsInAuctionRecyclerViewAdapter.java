package com.crossover.auctionsystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.model.Item;

import java.util.ArrayList;

/**
 * Created by suraj on 23/9/16.
 */

public class ViewItemsInAuctionRecyclerViewAdapter extends RecyclerView.Adapter<ViewItemsInAuctionRecyclerViewAdapter.ItemViewHolder> {

    private Context mContext;
    private ArrayList<Item> mItems;
    private static final int ITEM_AVAILABLE = 0;
    private static final int ITEM_NOT_AVAILABLE = 0;

    public ViewItemsInAuctionRecyclerViewAdapter(Context context, ArrayList<Item> items) {
        this.mItems = items;
        this.mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case ITEM_AVAILABLE :
        }
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_available_item_in_auction, parent,  false);
        ItemViewHolder viewHolder = new ItemViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public int getItemViewType(int position) {

        Item item = mItems.get(position);
        if(item.isItemSold()) {
            return ITEM_NOT_AVAILABLE;
        } else {
            return ITEM_AVAILABLE;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Item mItem;
        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView minimumBidAmountTextView;
        private TextView sellerNameTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.item_name_textview);
            descriptionTextView = (TextView) itemView.findViewById(R.id.item_description_textview);
            minimumBidAmountTextView = (TextView) itemView.findViewById(R.id.seller_name_textview);
            minimumBidAmountTextView = (TextView) itemView.findViewById(R.id.item_min_bid_amount_textview);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

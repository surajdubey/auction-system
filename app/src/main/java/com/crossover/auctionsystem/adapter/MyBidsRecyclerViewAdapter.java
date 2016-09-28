package com.crossover.auctionsystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;

import java.util.ArrayList;

/**
 * Created by suraj on 27/9/16.
 */

public class MyBidsRecyclerViewAdapter extends RecyclerView.Adapter<MyBidsRecyclerViewAdapter.BidViewHolder> {
    private Context mContext;
    private ArrayList<Bid> mBids;
    private ArrayList<Item> mItems;

    public MyBidsRecyclerViewAdapter(Context context, ArrayList<Bid> bids, ArrayList<Item> items) {
        this.mContext = context;
        this.mBids = bids;
        this.mItems = items;
    }

    @Override
    public BidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_my_bids, parent, false);
        return new BidViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BidViewHolder holder, int position) {
        Bid bid = mBids.get(position);
        Item item = mItems.get(position);

        holder.itemNameTextView.setText(item.getItemName());
        holder.itemDescriptionTextView.setText(item.getItemDescription());

        String bidAmountText = bid.getBidAmount() + "";
        holder.bidAmountTextView.setText(bidAmountText);

        if (bid.getBidStatus() == Bid.BID_WINNER) {
            holder.winnerTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mBids.size();
    }


    class BidViewHolder extends RecyclerView.ViewHolder {

        private TextView itemNameTextView;
        private TextView itemDescriptionTextView;
        private TextView bidAmountTextView;
        private TextView winnerTextView;

        public BidViewHolder(View itemView) {
            super(itemView);
            itemNameTextView = (TextView) itemView.findViewById(R.id.item_name_textview);
            itemDescriptionTextView = (TextView) itemView.findViewById(R.id.item_description_textview);
            bidAmountTextView = (TextView) itemView.findViewById(R.id.bid_amount_textview);
            winnerTextView = (TextView) itemView.findViewById(R.id.winner_textview);
        }
    }
}

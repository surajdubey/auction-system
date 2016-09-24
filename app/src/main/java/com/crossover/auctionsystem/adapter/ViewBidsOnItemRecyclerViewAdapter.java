package com.crossover.auctionsystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.model.Bid;

import java.util.ArrayList;

/**
 * Created by suraj on 24/9/16.
 */

public class ViewBidsOnItemRecyclerViewAdapter extends RecyclerView.Adapter<ViewBidsOnItemRecyclerViewAdapter.BidViewHolder> {

    private Context mContext;
    private ArrayList<Bid> mBids;

    public ViewBidsOnItemRecyclerViewAdapter(Context context, ArrayList<Bid> bids) {
        this.mContext = context;
        this.mBids = bids;
    }

    @Override
    public BidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_bid_on_item, parent, false);
        BidViewHolder viewHolder = new BidViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BidViewHolder holder, int position) {
        Bid bid = mBids.get(position);
        holder.setBid(bid);

        String bidAmountText = bid.getBidAmount() + "";
        holder.bidAmountTextView.setText(bidAmountText);
        holder.bidderNameTextView.setText(bid.getBidderName());
    }

    @Override
    public int getItemCount() {
        return mBids.size();
    }

    class BidViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView bidderNameTextView;
        private TextView bidAmountTextView;
        private ImageView declareWinnerImageView;
        private Bid bid;

        public BidViewHolder(View itemView) {
            super(itemView);

            bidderNameTextView = (TextView) itemView.findViewById(R.id.bidder_name_textview);
            bidAmountTextView = (TextView) itemView.findViewById(R.id.bid_amount_textview);
            declareWinnerImageView = (ImageView) itemView.findViewById(R.id.declare_win_imageview);
            declareWinnerImageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            //TODO: Declare winner here

        }

        public void setBid(Bid bid) {
            this.bid = bid;
        }
    }
}

package com.crossover.auctionsystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.activities.BidOnItemActivity;
import com.crossover.auctionsystem.interactor.ItemsInAuctionInteractor;
import com.crossover.auctionsystem.model.Item;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by suraj on 23/9/16.
 */

public class ViewItemsInAuctionRecyclerViewAdapter extends RecyclerView.Adapter<ViewItemsInAuctionRecyclerViewAdapter.ItemViewHolder> {

    private static final int ITEM_AVAILABLE = 0;
    private static final int ITEM_NOT_AVAILABLE = 0;
    private Context mContext;
    private ArrayList<Item> mItems;
    private ItemsInAuctionInteractor mItemsInAuctionInteractor;

    public ViewItemsInAuctionRecyclerViewAdapter(Context context, ArrayList<Item> items) {
        this.mItems = items;
        this.mContext = context;
        mItemsInAuctionInteractor = new ItemsInAuctionInteractor(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutResId;

        if (viewType == ITEM_AVAILABLE) {
            layoutResId = R.layout.row_available_item_in_auction;
        } else {
            layoutResId = R.layout.row_won_item_in_auction;
        }

        View itemView = LayoutInflater.from(mContext).inflate(layoutResId, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Item item = mItems.get(position);

        holder.setItem(item);
        holder.nameTextView.setText(item.getItemName());
        holder.descriptionTextView.setText(item.getItemDescription());

        if (holder.getItemViewType() == ITEM_AVAILABLE) {
            String minimumBidAmountText = item.getMinimumBidAmount() + "";
            holder.minimumBidAmountTextView.setText(minimumBidAmountText);

            holder.sellerNameTextView.setText(item.getSellerName());
        } else {
            String winnerName = mItemsInAuctionInteractor.getWinnerDisplayNameForItem(item.getItemId());

            holder.winnerNameTextView.setText(winnerName);

            String winnerAmountText = mItemsInAuctionInteractor.getWinnerBidAmountForItem(item.getItemId()) + "";
            holder.winnerAmountTextView.setText(winnerAmountText);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public int getItemViewType(int position) {

        Item item = mItems.get(position);
        if (item.isItemSold()) {
            return ITEM_NOT_AVAILABLE;
        } else {
            return ITEM_AVAILABLE;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Item item;
        private TextView nameTextView;
        private TextView descriptionTextView;

        private TextView minimumBidAmountTextView;
        private TextView sellerNameTextView;

        private TextView winnerNameTextView;
        private TextView winnerAmountTextView;

        ItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.item_name_textview);
            descriptionTextView = (TextView) itemView.findViewById(R.id.item_description_textview);
            minimumBidAmountTextView = (TextView) itemView.findViewById(R.id.item_min_bid_amount_textview);

            sellerNameTextView = (TextView) itemView.findViewById(R.id.seller_name_textview);
            winnerNameTextView = (TextView) itemView.findViewById(R.id.item_winner_name_textview);
            winnerAmountTextView = (TextView) itemView.findViewById(R.id.item_winner_amount_textview);

            itemView.setOnClickListener(this);
        }

        public void setItem(Item item) {
            this.item = item;
        }

        @Override
        public void onClick(View view) {

            if (mItemsInAuctionInteractor.isUserBiddingOnOwnItsOwnItem(item.getItemId())) {
                Toast.makeText(mContext, R.string.you_cannot_bid_on_your_item_error, Toast.LENGTH_SHORT).show();
            } else if (!item.isItemSold()) {

                //put selected item on Bus
                EventBus.getDefault().postSticky(item);
                Intent intent = new Intent(mContext, BidOnItemActivity.class);
                mContext.startActivity(intent);
            }

        }
    }
}

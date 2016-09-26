package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.interactor.BidOnItemInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.presenter.BidOnItemPresenter;
import com.crossover.auctionsystem.utils.ToolbarUtil;
import com.crossover.auctionsystem.view.BidOnItemView;

import org.greenrobot.eventbus.EventBus;

public class BidOnItemActivity extends AppCompatActivity implements BidOnItemView {

    private Context mContext = this;

    private TextView mNameTextView;
    private TextView mDescriptionTextView;
    private TextView mMinimumAmountTextView;
    private EditText mBidAmountEditText;
    private Button mSubmitBidButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_on_item);

        mNameTextView = (TextView) findViewById(R.id.item_name_textview);
        mDescriptionTextView = (TextView) findViewById(R.id.item_description_textview);
        mMinimumAmountTextView = (TextView) findViewById(R.id.item_min_bid_amount_textview);
        mBidAmountEditText = (EditText) findViewById(R.id.bid_amount_edittext);
        mSubmitBidButton = (Button) findViewById(R.id.submit_bid_button);

        setToolbar();

        BidOnItemView bidOnItemView = this;
        BidOnItemInteractor bidOnItemInteractor = new BidOnItemInteractor(mContext);

        final BidOnItemPresenter bidOnItemPresenter = new BidOnItemPresenter(bidOnItemView, bidOnItemInteractor);

        mSubmitBidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bidOnItemPresenter.submitBid();
            }
        });

        Item item = EventBus.getDefault().getStickyEvent(Item.class);
        bidOnItemPresenter.setItem(item);
    }

    private void setToolbar() {
        ToolbarUtil toolbarUtil = new ToolbarUtil(this);
        toolbarUtil.showToolbarWithBackButton(getString(R.string.place_bid));
    }

    @Override
    public String getBidAmount() {
        return mBidAmountEditText.getText().toString();
    }

    @Override
    public void showBidAmountInvalidError() {
        mBidAmountEditText.setError(getString(R.string.bid_amount_invalid_error));
    }

    @Override
    public void showBidAmountLessThanMinimumAmountError() {
        mBidAmountEditText.setError(getString(R.string.bid_amount_less_than_minimum_amount_error));
    }

    @Override
    public void showMinimumBidAmount(int minimumBidAmount) {
        String minimumBidAmountText = minimumBidAmount + "";
        mMinimumAmountTextView.setText(minimumBidAmountText);
    }

    @Override
    public void showName(String itemName) {
        mNameTextView.setText(itemName);
    }

    @Override
    public void showDescription(String itemDescription) {
        mDescriptionTextView.setText(itemDescription);
    }

    @Override
    public void showBidAddedSuccessMessage() {
        Toast.makeText(mContext, getString(R.string.bid_added_success_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeCurrentActivity() {
        finish();
    }
}

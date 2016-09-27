package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.interactor.AddItemInAuctionInteractor;
import com.crossover.auctionsystem.presenter.AddItemInAuctionPresenter;
import com.crossover.auctionsystem.utils.ToolbarUtil;
import com.crossover.auctionsystem.view.AddItemInAuctionView;

public class AddItemInAuctionActivity extends AppCompatActivity implements AddItemInAuctionView {

    private Context mContext = this;

    private EditText mNameEditText;
    private EditText mDescriptionEditText;
    private EditText mMinimumBiddingAmountEditText;
    private EditText mTargetBiddingAmountEditText;
    private Button mSubmitItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_in_auction);

        mNameEditText = (EditText) findViewById(R.id.item_name_edittext);
        mDescriptionEditText = (EditText) findViewById(R.id.item_description_edittext);
        mMinimumBiddingAmountEditText = (EditText) findViewById(R.id.item_minimum_amount_edittext);
        mTargetBiddingAmountEditText = (EditText) findViewById(R.id.target_amount_edittext);
        mSubmitItemButton = (Button) findViewById(R.id.submit_item_button);

        setToolbar();

        AddItemInAuctionView addItemInAuctionView = this;
        final AddItemInAuctionInteractor addItemInAuctionInteractor = new AddItemInAuctionInteractor(mContext);
        final AddItemInAuctionPresenter addItemInAuctionPresenter = new AddItemInAuctionPresenter(addItemInAuctionView, addItemInAuctionInteractor);

        mSubmitItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemInAuctionPresenter.submitItem();
            }
        });
    }

    private void setToolbar() {
        ToolbarUtil toolbarUtil = new ToolbarUtil(this);
        toolbarUtil.showToolbarWithBackButton(getString(R.string.add_item));
    }

    @Override
    public void showTargetAmountInvalidError() {
        mTargetBiddingAmountEditText.setError(getString(R.string.invalid_target_amount));
    }

    @Override
    public void showMinimumAmountInvalidError() {
        mMinimumBiddingAmountEditText.setError(getString(R.string.minimum_amount_invalid));
    }

    @Override
    public void showDescriptionLengthInvalidError() {
        mDescriptionEditText.setError(getString(R.string.description_invalid_error));
    }

    @Override
    public void showNameLengthInvalidError() {
        mNameEditText.setError(getString(R.string.name_invalid_error));
    }

    @Override
    public String getTargetAmount() {
        return mTargetBiddingAmountEditText.getText().toString();
    }

    @Override
    public String getMinimumAmount() {
        return mMinimumBiddingAmountEditText.getText().toString();
    }

    @Override
    public String getDescription() {
        return mDescriptionEditText.getText().toString();
    }

    @Override
    public String getName() {
        return mNameEditText.getText().toString();
    }

    @Override
    public void showItemAddedSuccessMessage() {
        Toast.makeText(mContext, R.string.item_added_success_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeCurrentActivity() {
        finish();
    }

    @Override
    public void showTargetAmountLessThanMinimumAmountError() {
        mTargetBiddingAmountEditText.setError(getString(R.string.target_amount_less_than_minimum_amount_error));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }
}

package com.crossover.auctionsystem.utils;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.crossover.auctionsystem.R;

/**
 * Created by suraj on 26/9/16.
 */

public class ToolbarUtil {
    private AppCompatActivity mActivity;
    public ToolbarUtil(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    public void showToolbarWithBackButton(String toolbarTitle) {
        Toolbar toolbar = (Toolbar) mActivity.findViewById(R.id.toolbar);
        TextView toolbarTitleTextView = (TextView) toolbar.findViewById(R.id.toolbar_title_textview);
        toolbarTitleTextView.setText(toolbarTitle);

        mActivity.setSupportActionBar(toolbar);

        ActionBar actionBar = mActivity.getSupportActionBar();

        if(actionBar!=null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
}

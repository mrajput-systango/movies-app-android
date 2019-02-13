package com.otb.mvvmboilerplate.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mohit Rajput on 13/2/19.
 * Parent activity of all other activities of the project
 */
public class BaseActivity extends AppCompatActivity {
    protected String TAG;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
    }

    protected void showLoading(@StringRes int stringResId) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(stringResId));
        }
        progressDialog.show();
    }

    protected void dismiss() {
        progressDialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

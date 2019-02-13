package com.otb.mvvmboilerplate.network;

import android.content.Context;
import android.support.annotation.NonNull;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ssaxena on 4/7/17.
 */

public abstract class BaseResponseCallback<T> implements Callback<T> {

    public final static int CODE_SESSION_EXPIRED = 401;
    public final static int CODE_FORBIDDEN = 403;
    public final static int BAD_REQUEST = 400;
    public final static int CODE_APP_TO_OLD = 301;
    private int retryCount;
    private Context context;

    public BaseResponseCallback() {
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) {
        int code = response.code();
        commonCallback(call, response, null);
        if (code >= 200 && code < 300) {
            success(call, response);
        } else if (code == CODE_SESSION_EXPIRED || code == CODE_FORBIDDEN) {
            unauthenticated(call, response);
        }
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull Throwable throwable) {
        if (throwable instanceof SocketTimeoutException) {
//            if (retryCount < 3) {
//                call.clone().enqueue(this);
//                retryCount++;
//            } else {
//                retryCount = 0;
//            }
        }
        commonCallback(call, null, throwable);
    }

    /**
     * Called for [200, 300) responses.
     */
    public abstract void success(Call call, Response<T> response);

    /**
     * Called when session gets expired of currently logged-in user. It is based on status code {@link #CODE_SESSION_EXPIRED}
     */
    public abstract void unauthenticated(Call call, Response<?> response);

    /**
     * Called every time to perform common tasks i.e. dismiss a progress dialog
     */
    public abstract void commonCallback(Call call, Response<?> response, Throwable throwable);
}

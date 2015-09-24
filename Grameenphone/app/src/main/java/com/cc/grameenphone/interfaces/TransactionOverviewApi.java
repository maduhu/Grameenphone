package com.cc.grameenphone.interfaces;

import com.cc.grameenphone.api_models.TransactionOverviewModel;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by aditlal on 20/09/15.
 */
public interface TransactionOverviewApi {

    @POST("/GPTxn/CelliciumSelector?LOGIN=Ussd_Bearer1&PASSWORD=U$$d_Int11&REQUEST_GATEWAY_CODE=J2ME&REQUEST_GATEWAY_TYPE=J2ME&requestText=")
    void fetchStatements(@Body JSONObject jsonObject, Callback<TransactionOverviewModel> cb);
}
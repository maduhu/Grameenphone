package com.cc.grameenphone.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.cc.grameenphone.R;
import com.cc.grameenphone.api_models.CompanyListModel;
import com.cc.grameenphone.interfaces.ManageAssociationApi;
import com.cc.grameenphone.utils.Logger;
import com.cc.grameenphone.utils.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.drakeet.materialdialog.MaterialDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class OtherPaymentElectricityFragment extends BaseTabFragment {


    ManageAssociationApi associationApi;
    ProgressDialog loadingDialog;
    @InjectView(R.id.custodial_radiogroup)
    RadioGroup custodialRadiogroup;
    @InjectView(R.id.companyRadioGroupScroll)
    ScrollView companyRadioGroupScroll;
    @InjectView(R.id.account_numbEdit)
    EditText accountNumbEdit;
    @InjectView(R.id.account_numb_container)
    TextInputLayout accountNumbContainer;
    @InjectView(R.id.bill_numbEdit)
    EditText billNumbEdit;
    @InjectView(R.id.bill_numb_container)
    TextInputLayout billNumbContainer;
    @InjectView(R.id.amountEditText)
    EditText amountEditText;
    @InjectView(R.id.amountTextInputLayout)
    TextInputLayout amountTextInputLayout;
    @InjectView(R.id.surchargeEditText)
    EditText surchargeEditText;
    @InjectView(R.id.surchargeTextInputLayout)
    TextInputLayout surchargeTextInputLayout;
    @InjectView(R.id.sbmt_btn)
    Button sbmtBtn;
    @InjectView(R.id.electricity_container)
    RelativeLayout electricityContainer;
    private String android_id;
    private PreferenceManager preferenceManager;

    MaterialDialog confirmationDialog;

    public static OtherPaymentElectricityFragment newInstance(Bundle b) {
        OtherPaymentElectricityFragment electricityTab = new OtherPaymentElectricityFragment();
        electricityTab.setArguments(b);
        return electricityTab;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_electricity, container, false);
        ButterKnife.inject(this, v);
        addRadioButtons(10);
        //TODO make amount and surcharge amount visible
        amountTextInputLayout.setVisibility(View.VISIBLE);
        surchargeTextInputLayout.setVisibility(View.VISIBLE);


        //submit Buttons onclick

        sbmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.confirmation_dialog, null);
                confirmationDialog = new MaterialDialog(getActivity());
                confirmationDialog.setView(dialogView);

                Button confirmButton = (Button) dialogView.findViewById(R.id.confirmDialogButton);
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        confirmationDialog.dismiss();
                    }
                });
                confirmationDialog.show();
            }
        });

       /* //changes in type 1 for NewAssociationActivity
        if (type == 1) {
            billNumbEdit.setVisibility(View.GONE);
            sbmtBtn.setText("CONFIRM");
            sbmtBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    confirmDialog = new MaterialDialog(getActivity());
                    *//*confirmDialog.setContentView(R.layout.association_conformation_dialog);
                    okay = (Button) confirmDialog.findViewById(R.id.resendButton);
                    confirmDialog.show();
                    confirmDialog.getWindow().setLayout(700, 300);
                    okay_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                        }
                    });
                    confirmDialog.setCanceledOnTouchOutside(true);*//*
                }
            });

        }*/

        return v;


    }

    public void addRadioButtons(int number) {

        for (int row = 0; row < 1; row++) {
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.VERTICAL);

            for (int i = 1; i <= number; i++) {
                RadioButton rdbtn = new RadioButton(getActivity());
                rdbtn.setId((row * 2) + i);
                rdbtn.setText("Radio " + rdbtn.getId());
                rdbtn.setTextColor(Color.parseColor("#666666"));
                ll.addView(rdbtn);
            }
            custodialRadiogroup.addView(ll);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleArguments();
    }

    private void handleArguments() {

        Bundle b;
        try {
            b = getArguments();
            Logger.d("argu", b.toString());
            type = getArguments().getInt("type");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void fetchList() {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject innerObject = new JSONObject();
            innerObject.put("DEVICEID", android_id);
            innerObject.put("AUTHTOKEN", preferenceManager.getAuthToken());
            innerObject.put("MSISDN", "017" + preferenceManager.getMSISDN());
            innerObject.put("TYPE", "FBILASCREQ");
            jsonObject.put("COMMAND", innerObject);
            Logger.d("sending json", jsonObject.toString());
            associationApi.fetchAssociaition(jsonObject, new Callback<CompanyListModel>() {
                @Override
                public void success(CompanyListModel companyListModel, Response response) {
                    Logger.d("Companyies ", companyListModel.toString());
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

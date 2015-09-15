package com.cc.grameenphone.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cc.grameenphone.R;
import com.cc.grameenphone.activity.ReferFriendsActivity;
import com.cc.grameenphone.activity.SelectContactsActivity;
import com.cc.grameenphone.activity.TransactionOverviewActivity;
import com.cc.grameenphone.activity.BillPaymentActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by rajkiran on 09/09/15.
 */
public class HomePage extends Fragment {
    @InjectView(R.id.prepaidOption)
    RadioButton radioprepaid;
    @InjectView(R.id.postpaidOption)
    RadioButton radiopostpaid;
    @InjectView(R.id.radioGroup)
    RadioGroup radiogroup;
    @InjectView(R.id.areaCode)
    TextView areacode;
    @InjectView(R.id.phoneNumber)
    EditText editphone;
    @InjectView(R.id.phone_container)
    TextInputLayout phoneContainer;
    @InjectView(R.id.other_flex)
    TextView otherFlex;
    @InjectView(R.id.top_container1)
    RelativeLayout topContainer1;
    @InjectView(R.id.editamt)
    EditText editamt;
    @InjectView(R.id.amount_container)
    TextInputLayout amountContainer;
    @InjectView(R.id.billpay)
    ImageView billpay;
    @InjectView(R.id.bill_text)
    TextView billText;
    @InjectView(R.id.billPayment)
    RelativeLayout billPay;
    @InjectView(R.id.trans_icon)
    ImageView transIcon;
    @InjectView(R.id.transc_text)
    TextView transcText;
    @InjectView(R.id.transactionOverview)
    RelativeLayout transactionView;
    @InjectView(R.id.emergencyicon)
    ImageView emergencyicon;
    @InjectView(R.id.emergency_text)
    TextView emergencyText;
    @InjectView(R.id.emergencyCall)
    RelativeLayout emergency;
    @InjectView(R.id.friends)
    ImageView friends;
    @InjectView(R.id.friends_text)
    TextView friendsText;
    @InjectView(R.id.referFriends)
    RelativeLayout referFrnd;
    private Button flexiBtn;

    public HomePage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_layout, container, false);
        RelativeLayout transactionView1 = (RelativeLayout) rootView.findViewById(R.id.transactionOverview);
        RelativeLayout transactionView2 = (RelativeLayout) rootView.findViewById(R.id.billPayment);
        RelativeLayout transactionView3 = (RelativeLayout) rootView.findViewById(R.id.emergencyCall);
        RelativeLayout transactionView4 = (RelativeLayout) rootView.findViewById(R.id.referFriends);
        transactionView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TransactionOverviewActivity.class));
            }
        });
        transactionView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BillPaymentActivity.class));
            }
        });
        transactionView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ReferFriendsActivity.class));
            }
        });
        TextView flexText = (TextView) rootView.findViewById(R.id.other_flex);
        flexText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SelectContactsActivity.class));

            }
        });
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radio_pre = (RadioButton) radioGroup.findViewById(R.id.prepaidOption);
                RadioButton radio_post = (RadioButton) radioGroup.findViewById(R.id.postpaidOption);
                if (radio_pre.isChecked()) {

                } else if (radio_post.isChecked()) {
                }

            }
        });
        flexiBtn = (Button) rootView.findViewById(R.id.flexi_btn);
        flexiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupview = inflater.inflate(R.layout.flexi_dialog_layout, null);

                final MaterialDialog materialDialog = new MaterialDialog(getActivity()).setContentView(popupview);
                materialDialog.setCanceledOnTouchOutside(true);
                materialDialog.show();
                Button buttonOk = (Button) popupview.findViewById(R.id.okButton);
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        materialDialog.dismiss();
                    }
                });
            }
        });
        // Inflate the layout for this fragment
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
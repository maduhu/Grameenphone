package com.cc.grameenphone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cc.grameenphone.R;
import com.cc.grameenphone.api_models.TransactionOverviewData;
import com.cc.grameenphone.views.RippleView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TransactionOverviewDeatilsActivity extends AppCompatActivity {


    TransactionOverviewData data;
    @InjectView(R.id.image_icon_back)
    ImageView imageIconBack;
    @InjectView(R.id.backRipple)
    RippleView backRipple;
    @InjectView(R.id.text_tool)
    TextView textTool;
    @InjectView(R.id.toolbar)
    Toolbar transactionToolbar;
    @InjectView(R.id.dueDate)
    TextView dateText;
    @InjectView(R.id.enterDate)
    TextView enterDate;
    @InjectView(R.id.accountText)
    TextView serText;
    @InjectView(R.id.accountTextView)
    TextView serviceTextView;
    @InjectView(R.id.second_top)
    RelativeLayout secondTop;
    @InjectView(R.id.billText)
    TextView cmpnyText;
    @InjectView(R.id.companyTextView)
    TextView companyNameTextView;
    @InjectView(R.id.third_top)
    RelativeLayout thirdTop;
    @InjectView(R.id.amt_txt)
    TextView amtTxt;
    @InjectView(R.id.amountTextView)
    TextView amountTextView;
    @InjectView(R.id.fourth_top)
    RelativeLayout fourthTop;
    @InjectView(R.id.surchargeText)
    TextView txnText;
    @InjectView(R.id.surchargeTextView)
    TextView transactionNumberTextView;
    @InjectView(R.id.fifth_top)
    RelativeLayout fifthTop;
    String date;
    @InjectView(R.id.amt_sc_txt)
    TextView amtScTxt;
    @InjectView(R.id.amountSCTextView)
    TextView amountSCTextView;
    @InjectView(R.id.fourth_half_top)
    RelativeLayout fourthHalfTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_overview_details);
        ButterKnife.inject(this);
        textTool.setText("Transaction Overview");

        handleRipples();

        handleExtras();
    }


    private void handleRipples() {
        backRipple.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                backClick();
            }
        });
    }

    private void handleExtras() {
        try {
            Bundle b = getIntent().getExtras();

            data = (TransactionOverviewData) b.get("transaction_obj");

            //   date = b.getString("transactionMap");
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void backClick() {
        finish();
    }

    private void initData() {

        enterDate.setText("" + data.getTXNDATE());
        serviceTextView.setText(data.getSERVICE());
        companyNameTextView.setText("" + data.getFROMTO());
        amountTextView.setText("৳ " + data.getTXNAMT());
        transactionNumberTextView.setText(data.getTXNID());
        amountSCTextView.setText("৳ " +data.getTXNSC());

    }


}

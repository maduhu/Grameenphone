package com.cc.grameenphone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cc.grameenphone.R;
import com.cc.grameenphone.api_models.BalanceEnquiryModel;
import com.cc.grameenphone.fragments.DemoFragment;
import com.cc.grameenphone.fragments.HomeFragment;
import com.cc.grameenphone.fragments.ManageFavoriteFragment;
import com.cc.grameenphone.fragments.PinChangeFragment;
import com.cc.grameenphone.fragments.ProfileFragment;
import com.cc.grameenphone.fragments.TermsConditionFragment;
import com.cc.grameenphone.generator.ServiceGenerator;
import com.cc.grameenphone.interfaces.WalletCheckApi;
import com.cc.grameenphone.utils.Logger;
import com.cc.grameenphone.utils.PreferenceManager;
import com.cc.grameenphone.views.RippleView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.drakeet.materialdialog.MaterialDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.container_body)
    FrameLayout containerBody;
    @InjectView(R.id.navigation_view)
    NavigationView navigationView;
    @InjectView(R.id.drawer)
    DrawerLayout drawerLayout;
    FragmentTransaction fragmentTransaction;
    Fragment fragment;
    @InjectView(R.id.toolbar_text)
    TextView toolbarTextView;
    @InjectView(R.id.icon1)
    ImageButton icon1;
    @InjectView(R.id.icon2)
    ImageButton icon2;
    PreferenceManager preferenceManager;
    @InjectView(R.id.walletLabel)
    TextView walletLabel;
    @InjectView(R.id.icon1Ripple)
    RippleView icon1Ripple;
    @InjectView(R.id.icon2Ripple)
    RippleView icon2Ripple;
    private String android_id;
    private WalletCheckApi walletCheckApi;
    MaterialDialog logoutDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grameenhome);
        ButterKnife.inject(this);
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        setSupportActionBar(toolbar);
        preferenceManager = new PreferenceManager(HomeActivity.this);
        fragment = new HomeFragment();
        toolbarTextView.setText("Home");
        getSupportActionBar().setTitle("");
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();

        getWalletBalance();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_1:
                        fragment = new HomeFragment();
                        //getSupportActionBar().setTitle("Home");
                        toolbarTextView.setText("Home");
                        icon1.setImageDrawable(getResources().getDrawable(R.drawable.icon_wallet_balance));
                        icon2.setImageDrawable(getResources().getDrawable(R.drawable.icon_notification));
                        walletLabel.setVisibility(View.VISIBLE);
                        icon1Ripple.setVisibility(View.VISIBLE);
                        icon2Ripple.setVisibility(View.VISIBLE);
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.navigation_item_2:
                        fragment = new ProfileFragment();
                        //   getSupportActionBar().setTitle("Profile");
                        toolbarTextView.setText("Profile");
                        icon1.setImageDrawable(getResources().getDrawable(R.drawable.icon_refresh));
                        icon2.setImageDrawable(getResources().getDrawable(R.drawable.icon_edit));
                        walletLabel.setVisibility(View.GONE);
                        icon1Ripple.setVisibility(View.VISIBLE);
                        icon2Ripple.setVisibility(View.VISIBLE);
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.navigation_item_3:
                        fragment = new ManageFavoriteFragment();
                        //  getSupportActionBar().setTitle("Manage Favorites");
                        toolbarTextView.setText("Manage Favorites");
                        // icon1.setVisibility(View.GONE);
                        icon2.setImageDrawable(getResources().getDrawable(R.drawable.icon_add));
                        icon1Ripple.setVisibility(View.GONE);
                        icon2Ripple.setVisibility(View.VISIBLE);
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.navigation_item_4:
                        fragment = new PinChangeFragment();
                        // getSupportActionBar().setTitle("Pin Change");
                        toolbarTextView.setText("Pin Change");
                        icon1Ripple.setVisibility(View.GONE);
                        icon2Ripple.setVisibility(View.GONE);
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.navigation_item_5:
                        fragment = new DemoFragment();
                        // getSupportActionBar().setTitle("Demo");
                        toolbarTextView.setText("Demo");
                       /* icon1.setImageDrawable(getResources().getDrawable(R.drawable.icon_wallet_balance));
                        icon2.setImageDrawable(getResources().getDrawable(R.drawable.icon_notification));*/
                        icon1Ripple.setVisibility(View.GONE);
                        icon2Ripple.setVisibility(View.GONE);
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.navigation_item_6:
                        fragment = new TermsConditionFragment();
                        // getSupportActionBar().setTitle("Terms & Condition");
                        toolbarTextView.setText("Terms & Condition");
                        /*icon1.setImageDrawable(getResources().getDrawable(R.drawable.icon_wallet_balance));
                        icon2.setImageDrawable(getResources().getDrawable(R.drawable.icon_notification));*/
                        icon1Ripple.setVisibility(View.GONE);
                        icon2Ripple.setVisibility(View.GONE);
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.navigation_item_7:

                        logoutDialog = new MaterialDialog(HomeActivity.this);
                        logoutDialog.setTitle("Logout");
                        logoutDialog.setMessage("Are you sure ?");
                        logoutDialog.setPositiveButton("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                preferenceManager.setAuthToken("");
                                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                                preferenceManager.setMSISDN("");
                                finish();
                            }
                        });
                        logoutDialog.setNegativeButton("Cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                logoutDialog.dismiss();
                            }
                        });

                        logoutDialog.show();


                        return true;
                    default:
                        return true;

                }

            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();


        handleRipple();
    }

    private void handleRipple() {
        icon2Ripple.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.container_body);

                if (f instanceof ManageFavoriteFragment) {
                    startActivity(new Intent(HomeActivity.this, AddFavoriteContactsActivity.class));
                }
                if (f instanceof ProfileFragment) {
                    //startActivity(new Intent(GrameenHomeActivity.this, EditProfileActivity.class));
                }
            }
        });
    }

    private void getWalletBalance() {
        walletCheckApi = ServiceGenerator.createService(WalletCheckApi.class);
        android_id = Settings.Secure.getString(HomeActivity.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject innerObject = new JSONObject();
            innerObject.put("DEVICEID", android_id);
            innerObject.put("AUTHTOKEN", preferenceManager.getAuthToken());
            innerObject.put("MSISDN", "017" + preferenceManager.getMSISDN());
            innerObject.put("TYPE", "CBEREQ");
            jsonObject.put("COMMAND", innerObject);
            Logger.d("wallet request ", jsonObject.toString());
            walletCheckApi.checkBalance(jsonObject, new Callback<BalanceEnquiryModel>() {
                @Override
                public void success(BalanceEnquiryModel balanceEnquiryModel, Response response) {
                    if (balanceEnquiryModel.getCOMMAND().getTXNSTATUS().equalsIgnoreCase("200")) {
                        Logger.d("Balance", balanceEnquiryModel.toString());
                        walletLabel.setText("৳ " + balanceEnquiryModel.getCOMMAND().getBALANCE());
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Logger.e("Balance", error.getMessage());
                }
            });
        } catch (JSONException e) {

        }
    }


}
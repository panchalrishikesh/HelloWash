package com.hw.hellowash.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.hw.hellowash.R;
import com.hw.hellowash.Utilities;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;


public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private Context mContext;
    private TextView customer_name;
    private Button logout_btn;
    private android.support.v4.app.FragmentTransaction transaction;
    private android.support.v4.app.FragmentManager manager;
    public TextView toolbar_title;
    private TextView credit_points_text;
    CircleImageView header_profile_image;


    public void applyTheme(Context mContext) {
        this.mContext = mContext;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        displayActionBar();
        ActionBar avy = getSupportActionBar();
        avy.setTitle(null);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_drawer_toggle);
        if (Utilities.getBooleanPref(getApplicationContext(), "HasLogged_In", false)) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    } else {
                        drawer.openDrawer(GravityCompat.START);
                    }
                }
            });

        } else {
            toolbar.setNavigationIcon(null);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        logout_btn = (Button) findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(this);

        customer_name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.customer_name);
        customer_name.setText(Utilities.getPref(mContext, "Display_Name", ""));

        header_profile_image = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.header_profile_image);
        if (!TextUtils.isEmpty(Utilities.getPref(this, "Profile_pic", ""))) {
            Picasso.get().load(new File(Utilities.getPref(this, "Profile_pic", ""))).into(header_profile_image);
        }

        manager = getSupportFragmentManager();

        Menu navigationMenu = navigationView.getMenu();
        for (int i = 0; i < navigationMenu.size(); i++) {
            MenuItem mi = navigationMenu.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    Utilities.applyFontToMenuItem(subMenuItem, this);
                }
            }

            //the method we have create in activity
            Utilities.applyFontToMenuItem(mi, this);
        }

        Menu menuNav = navigationView.getMenu();
        MenuItem element = menuNav.findItem(R.id.credit_points);
        element.setActionView(R.layout.credit_points_layout);

        credit_points_text = (TextView) element.getActionView().findViewById(R.id.credit_points_text);
        credit_points_text.setText("50");

        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        if (toolbar_title != null) {
            toolbar_title.setText(Utilities.getPref(this, "CompanyName", ""));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.credit_points) {

            closeDrawer();
        } else if (id == R.id.my_order) {

            closeDrawer();
        } else if (id == R.id.serviceable_areas) {

            closeDrawer();
        } else if (id == R.id.refer) {

            closeDrawer();
        } else if (id == R.id.faq) {
            Intent search = new Intent(this, FaqsActivity.class);
            startActivity(search);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);
            closeDrawer();
        } else if (id == R.id.support) {
            Intent search = new Intent(this, SupportActivity.class);
            startActivity(search);
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);
            closeDrawer();
        } else if (id == R.id.rate_us) {

            closeDrawer();
        }


        return true;
    }

    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    public void displayActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_btn:
                performLogout();
                break;
        }
    }

    LoginActivity act;
    private void performLogout() {
        if(Utilities.getPref(mContext, "LoggedInWith", "").equalsIgnoreCase("Facebook")){
            disconnectFromFacebook();
        }else if(Utilities.getPref(mContext, "LoggedInWith", "").equalsIgnoreCase("Gmail")){
            gmailLogoutCall();
        }else {
            Utilities.clearPreferences(getApplicationContext());
            Utilities.savebooleanPref(getApplicationContext(), "HasLogged_In", false);
            Intent returnTOLogin = new Intent(getApplicationContext(), LoginActivity.class);
            returnTOLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            returnTOLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(returnTOLogin);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down);
        }
    }

    private void gmailLogoutCall() {
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, BaseActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                dialog.dismiss();
                                Utilities.clearPreferences(getApplicationContext());
                                Utilities.savebooleanPref(getApplicationContext(), "HasLogged_In", false);
                                Intent returnTOLogin = new Intent(getApplicationContext(), LoginActivity.class);
                                returnTOLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                returnTOLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(returnTOLogin);
                                finish();
                                overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down);
                            }
                        });
            }
        }, 2000);

    }

    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
                Utilities.clearPreferences(getApplicationContext());
                Utilities.savebooleanPref(getApplicationContext(), "HasLogged_In", false);
                Intent returnTOLogin = new Intent(getApplicationContext(), LoginActivity.class);
                returnTOLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                returnTOLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(returnTOLogin);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down);
            }
        }).executeAsync();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("", "onConnectionFailed:" + connectionResult);
    }
}

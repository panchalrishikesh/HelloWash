package com.hw.hellowash.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.hw.hellowash.R;
import com.hw.hellowash.Utilities;
import com.hw.hellowash.fragments.AddressFragment;
import com.hw.hellowash.fragments.NameFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private FragmentManager manager;
    private NameFragment nameFragment_Fragment;
    private FragmentTransaction transaction;

    @BindView(R.id.parent)
    LinearLayout parent;

    AddressFragment addressFrag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        manager = getSupportFragmentManager();

        nameFragment_Fragment = new NameFragment();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, nameFragment_Fragment);
        transaction.addToBackStack("NameFragment");
        transaction.commit();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            addressFrag.onActivityResult(requestCode,resultCode,data);
        }
    }



    @Override
    public void onBackPressed() {
        String fragmentTag = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
        if (fragmentTag.equalsIgnoreCase("NameFragment")) {

            AlertDialog.Builder alert_Dialog = new AlertDialog.Builder(this);
            alert_Dialog.setCancelable(false);
            alert_Dialog.setTitle("Alert");
            alert_Dialog.setMessage("Do you want to exit from App?");
            alert_Dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    gmailLogoutCall();
                }
            });

            alert_Dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert_Dialog.show();

        }else{
            manager.popBackStack();
        }
    }

    private void gmailLogoutCall() {
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, ProfileActivity.this)
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
                                ProfileActivity.this.finish();
                                overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down);
                            }
                        });
            }
        }, 2000);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("", "onConnectionFailed:" + connectionResult);
    }
}

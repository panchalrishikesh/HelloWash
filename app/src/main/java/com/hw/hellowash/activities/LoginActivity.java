package com.hw.hellowash.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.gson.JsonObject;
import com.hw.hellowash.BuildConfig;
import com.hw.hellowash.R;
import com.hw.hellowash.Utilities;
import com.hw.hellowash.networkcall.ApiClient;
import com.hw.hellowash.networkcall.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    //@BindView(R.id.video_view) VideoView video_view;
    @BindView(R.id.emailText) EditText emailText;
    @BindView(R.id.passwordText) EditText passwordText;
    @BindView(R.id.login_btn) Button login_btn;

    @BindView(R.id.facebook_lyt) FrameLayout facebook_lyt;
    @BindView(R.id.google_lyt) FrameLayout google_lyt;

    @BindView(R.id.gmail_login) SignInButton gmail_login;
    @BindView(R.id.gplus) ImageView gplus;

    @BindView(R.id.facebook_login) LoginButton FBloginButton;
    @BindView(R.id.fb) ImageView fb;
    @BindView(R.id.signup_btn) TextView signup_btn;

    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    private ProgressDialog mProgressDialog;
    private CallbackManager callbackManager;

    /***********API call decelaration**************/

    Call<JsonObject> userLogin_Call;
    private ApiInterface apiService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_2);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient(LoginActivity.this).create(ApiInterface.class);

        callbackManager = CallbackManager.Factory.create();
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        findViews();

    }

    private void findViews() {

        emailText.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/quicksand_bold.ttf"));
        passwordText.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/quicksand_bold.ttf"));

        FBloginButton.setReadPermissions("email");
        FBloginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserDetails(loginResult);
            }

            @Override
            public void onCancel() {
                Log.d("onCancel", "onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("onError", "onError");
                Log.d("LoginActivity", exception.getCause().getMessage());
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                //.requestProfile()
                //.requestScopes(new Scope("Phone"))
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, LoginActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        gmail_login.setSize(SignInButton.SIZE_STANDARD);
        gmail_login.setBackgroundResource(R.mipmap.ic_google);
        //gmail_login.set
        gmail_login.setScopes(gso.getScopeArray());

    }

    @OnClick({R.id.login_btn, R.id.signup_btn})
    public void performClick(View view){
        switch (view.getId()){
            case R.id.signup_btn:
                Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);
                break;

            case R.id.login_btn:
                break;
        }

    }

    @OnClick(R.id.facebook_lyt)
    public void performFBLogin(){
        FBloginButton.performClick();
    }

    @OnClick(R.id.google_lyt)
    public void performGPlusLogin(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    protected void getUserDetails(LoginResult loginResult) {


        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject json_object, GraphResponse response) {
                        try {
                            if(json_object!=null){
                                if(json_object.get("email").toString()!=null ) {
                                    Utilities.savePref(LoginActivity.this, "email", json_object.get("email").toString());
                                }
                                if(json_object.get("picture").toString()!=null){

                                    try {
                                        URL profile_pic = new URL(
                                                "http://graph.facebook.com/" + json_object.getString("id") + "/picture?type=large");
                                        Log.i("profile_pic", profile_pic + "");
                                        Utilities.savePref(LoginActivity.this, "Large_Photo", profile_pic.toString());
                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                    }

                                    Utilities.savePref(LoginActivity.this, "Photo", json_object.get("picture").toString());
                                }
                                if(json_object.get("name").toString()!=null) {
                                    Utilities.savePref(LoginActivity.this, "Display_Name", json_object.get("name").toString());
                                }

                                Utilities.savePref(LoginActivity.this, "LoggedInWith", "Facebook");
                                Utilities.savebooleanPref(LoginActivity.this, "HasLogged_In", true);
                                Intent fromFacebook_Intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                startActivity(fromFacebook_Intent);
                                finish();
                                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);

                                //ShowProfileCapturePopup();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email, picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();

    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e(TAG, "display name: " + acct.getDisplayName());
            updateUI(acct,true);
        } else {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void updateUI(GoogleSignInAccount accdetails,boolean isSignedIn) {
        if(isSignedIn) {
            Utilities.showToast(LoginActivity.this,accdetails.getDisplayName());
            if(accdetails.getEmail()!=null ) {
                Utilities.savePref(LoginActivity.this, "Email", accdetails.getEmail());
            }
            if(accdetails.getPhotoUrl()!=null){
                Utilities.savePref(LoginActivity.this, "Photo", String.valueOf(accdetails.getPhotoUrl()));
            }
            if(accdetails.getDisplayName()!=null) {
                Utilities.savePref(LoginActivity.this, "Display_Name", accdetails.getDisplayName());
            }

            Utilities.savePref(LoginActivity.this, "LoggedInWith", "Gmail");
            Utilities.savebooleanPref(LoginActivity.this, "HasLogged_In", true);
            Intent fromFacebook_Intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(fromFacebook_Intent);
            finish();
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);

            //ShowProfileCapturePopup();
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }


}

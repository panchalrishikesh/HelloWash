package com.hw.hellowash.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hw.hellowash.R;
import com.hw.hellowash.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupportActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title) TextView toolbar_title;
    @BindView(R.id.place_call_btn) Button place_call_btn;
    @BindView(R.id.invoke_mail) Button invoke_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        ButterKnife.bind(this);

        SetupUI();

    }

    private void SetupUI() {

        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        displayActionBar();
        ActionBar avy = getSupportActionBar();
        avy.setTitle(null);

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_from_left);
            }
        });

        if(toolbar_title!=null){
            toolbar_title.setText(getResources().getString(R.string.menu_support));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.place_call_btn)
    public void contactSupport(){
        Calendar currentTime = Calendar.getInstance();
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss a");
        Date dt = currentTime.getTime();
        String str = ft.format(dt);

        if(Utilities.isWithingSH(str)) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + getResources().getString(R.string.support_number)));
            if (ContextCompat.checkSelfPermission(SupportActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((AppCompatActivity) SupportActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 0);
            } else {
                startActivity(callIntent);
            }
        }else{
            Utilities.showToast(SupportActivity.this, "Call Us between 9:30 AM and 6:30 PM");
        }
    }

    @OnClick(R.id.invoke_mail)
    public void sendMail(){
        selectMailID();
    }

    private void selectMailID() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(SupportActivity.this);
        builderSingle.setTitle("Select Mail Id");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SupportActivity.this, R.layout.simple_textview);
        arrayAdapter.clear();
        arrayAdapter.add("Support : support@hellowash.co.in");
        arrayAdapter.add("General queries : sup@hellowash.co.in");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String strName = arrayAdapter.getItem(which);
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                //emailIntent.setType("message/rfc822");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{strName.split(":")[1].trim()});
                //emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Request Support");
                startActivity(Intent.createChooser(emailIntent, "Send Email"));
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builderSingle.create();
        ListView listView = dialog.getListView();
        listView.setVerticalScrollBarEnabled(false);
        listView.setDivider(new ColorDrawable(Color.parseColor("#44BDC8")));
        listView.setDividerHeight(1);
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public void displayActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}

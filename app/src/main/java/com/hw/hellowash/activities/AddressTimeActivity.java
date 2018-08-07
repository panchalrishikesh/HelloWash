package com.hw.hellowash.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dpizarro.uipicker.library.picker.PickerUI;
import com.dpizarro.uipicker.library.picker.PickerUISettings;
import com.hw.hellowash.R;
import com.hw.hellowash.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressTimeActivity extends AppCompatActivity
{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title_txt) TextView title_txt;
    @BindView(R.id.no_address_lyt) LinearLayout no_address_lyt;
    @BindView(R.id.change_address_btn) Button change_address_btn;
    @BindView(R.id.pickup_picker) PickerUI pickup_picker;

    List<String> pickupDates = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_time);
        ButterKnife.bind(this);

        setupUI();
    }

    private void setupUI() {
        setSupportActionBar(toolbar);
        displayActionBar();
        ActionBar avy = getSupportActionBar();
        avy.setTitle(null);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_from_left);
            }
        });



        Calendar dayAfterTomorrow = Calendar.getInstance();
        dayAfterTomorrow.add(Calendar.DATE, 2);

        Calendar day4 = Calendar.getInstance();
        day4.add(Calendar.DATE, 3);

        Calendar day5 = Calendar.getInstance();
        day5.add(Calendar.DATE, 4);

        pickupDates.add("Today");
        pickupDates.add("Tomorrow");
        pickupDates.add(Utilities.getFormatedMonthDate(dayAfterTomorrow.getTime()));
        pickupDates.add(Utilities.getFormatedMonthDate(day4.getTime()));
        pickupDates.add(Utilities.getFormatedMonthDate(day5.getTime()));

        PickerUISettings pickerUISettings = new PickerUISettings.Builder()
                .withItems(pickupDates)
                .withBackgroundColor(getResources().getColor(R.color.colorPrimary))
                .withItemsClickables(false)
                .withUseBlur(false)
                .build();

        pickup_picker.setSettings(pickerUISettings);

        pickup_picker.setItems(this, pickupDates);
        pickup_picker.setColorTextCenter(R.color.colorPrimary);
        //pickup_picker.setColorTextNoCenter(R.color.background_picker);
        pickup_picker.setBackgroundColorPanel(R.color.color_white);
        pickup_picker.setLinesColor(R.color.color_gray);
        pickup_picker.setItemsClickables(false);
        pickup_picker.setAutoDismiss(false);
        pickup_picker.isPanelShown();
        pickup_picker.slide();

        pickup_picker.setOnClickItemPickerUIListener(new PickerUI.PickerUIItemClickListener() {
            @Override
            public void onItemClickPickerUI(int which, int position, String valueResult) {
                Utilities.showToast(AddressTimeActivity.this, valueResult);
            }
        });
    }

    @OnClick({R.id.no_address_lyt, R.id.change_address_btn})
    public void performClick(View view){
        switch (view.getId()){
            case R.id.no_address_lyt:
                Intent setpickupTimeIntent = new Intent(AddressTimeActivity.this, AddressActivity.class);
                startActivity(setpickupTimeIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;

            case R.id.change_address_btn:
                Intent setpickupTimeIntent2 = new Intent(AddressTimeActivity.this, AddressActivity.class);
                startActivity(setpickupTimeIntent2);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
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

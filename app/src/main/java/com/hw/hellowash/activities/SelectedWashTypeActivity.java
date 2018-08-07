package com.hw.hellowash.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.hw.hellowash.R;
import com.hw.hellowash.adapters.ViewPagerFragmentAdapter;
import com.hw.hellowash.fragments.HouseHold_Fragment;
import com.hw.hellowash.fragments.Men_Fragment;
import com.hw.hellowash.fragments.Women_Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectedWashTypeActivity extends AppCompatActivity
{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title_txt) TextView title_txt;
    @BindView(R.id.viewpager) ViewPager viewpager;
    @BindView(R.id.tablayout) TabLayout tablayout;

    @BindView(R.id.fab) FloatingActionsMenu fab;
    @BindView(R.id.wash_n_fold) FloatingActionButton wash_n_fold;
    @BindView(R.id.wash_n_iron) FloatingActionButton wash_n_iron;
    @BindView(R.id.steam_iron) FloatingActionButton steam_iron;
    @BindView(R.id.dry_clean) FloatingActionButton dry_clean;
    @BindView(R.id.dying) FloatingActionButton dying;


    private ViewPagerFragmentAdapter adapter;
    private Men_Fragment men_Fragment;
    private Women_Fragment women_Fragment;
    private HouseHold_Fragment houseHold_Fragment;

    Intent i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_washtype);
        ButterKnife.bind(this);

        setupUI();
        i = getIntent();
        if(i!=null){
            String washType = i.getStringExtra("SelectedWashType");
            if(washType!=null && !washType.equalsIgnoreCase("")){
                title_txt.setText(washType);
            }
        }
        setupViewPager(viewpager);
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
    }

    @OnClick({R.id.wash_n_fold, R.id.wash_n_iron, R.id.steam_iron, R.id.dry_clean, R.id.dying})
    public void performClick(View view){
        switch (view.getId()){
            case R.id.wash_n_fold:
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                Intent washnFoldIntent = getIntent();
                washnFoldIntent.putExtra("SelectedWashType", getResources().getString(R.string.wash_n_fold));
                startActivity(washnFoldIntent);
                break;

            case R.id.wash_n_iron:
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                Intent washnIronIntent = getIntent();
                washnIronIntent.putExtra("SelectedWashType", getResources().getString(R.string.wash_n_iron));
                startActivity(washnIronIntent);
                break;

            case R.id.steam_iron:
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                Intent steamIronIntent = getIntent();
                steamIronIntent.putExtra("SelectedWashType", getResources().getString(R.string.stean_iron));
                startActivity(steamIronIntent);
                break;

            case R.id.dry_clean:
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                Intent dryCleanIntent = getIntent();
                dryCleanIntent.putExtra("SelectedWashType", getResources().getString(R.string.dry_clean));
                startActivity(dryCleanIntent);
                break;

            case R.id.dying:
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                Intent dyingIntent = getIntent();
                dyingIntent.putExtra("SelectedWashType", getResources().getString(R.string.dying));
                startActivity(dyingIntent);
                break;
        }
    }

    private void setupViewPager(ViewPager viewpager) {

        adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());

        men_Fragment = new Men_Fragment();
        adapter.addFrag(men_Fragment, "Men");

        women_Fragment = new Women_Fragment();
        adapter.addFrag(women_Fragment, "Women");

        houseHold_Fragment = new HouseHold_Fragment();
        adapter.addFrag(houseHold_Fragment, "House Hold");

        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }

    public void displayActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}

package com.hw.hellowash.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hw.hellowash.Custom.ExpandableLayout;
import com.hw.hellowash.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FaqsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_title) TextView toolbar_title;

    @BindView(R.id.faq_1_lyt) LinearLayout faq_1_lyt;
    @BindView(R.id.faq_2_lyt) LinearLayout faq_2_lyt;
    @BindView(R.id.faq_3_lyt) LinearLayout faq_3_lyt;
    @BindView(R.id.faq_4_lyt) LinearLayout faq_4_lyt;
    @BindView(R.id.faq_5_lyt) LinearLayout faq_5_lyt;
    @BindView(R.id.faq_6_lyt) LinearLayout faq_6_lyt;
    @BindView(R.id.faq_7_lyt) LinearLayout faq_7_lyt;
    @BindView(R.id.faq_8_lyt) LinearLayout faq_8_lyt;
    @BindView(R.id.faq_9_lyt) LinearLayout faq_9_lyt;
    @BindView(R.id.faq_10_lyt) LinearLayout faq_10_lyt;
    @BindView(R.id.faq_11_lyt) LinearLayout faq_11_lyt;
    @BindView(R.id.faq_12_lyt) LinearLayout faq_12_lyt;
    @BindView(R.id.faq_13_lyt) LinearLayout faq_13_lyt;
    @BindView(R.id.faq_14_lyt) LinearLayout faq_14_lyt;
    @BindView(R.id.faq_15_lyt) LinearLayout faq_15_lyt;
    @BindView(R.id.faq_16_lyt) LinearLayout faq_16_lyt;
    @BindView(R.id.faq_17_lyt) LinearLayout faq_17_lyt;

    @BindView(R.id.fa1_1_iv) ImageView fa1_1_iv;
    @BindView(R.id.fa1_2_iv) ImageView fa1_2_iv;
    @BindView(R.id.fa1_3_iv) ImageView fa1_3_iv;
    @BindView(R.id.fa1_4_iv) ImageView fa1_4_iv;
    @BindView(R.id.fa1_5_iv) ImageView fa1_5_iv;
    @BindView(R.id.fa1_6_iv) ImageView fa1_6_iv;
    @BindView(R.id.fa1_7_iv) ImageView fa1_7_iv;
    @BindView(R.id.fa1_8_iv) ImageView fa1_8_iv;
    @BindView(R.id.fa1_9_iv) ImageView fa1_9_iv;
    @BindView(R.id.fa1_10_iv) ImageView fa1_10_iv;
    @BindView(R.id.fa1_11_iv) ImageView fa1_11_iv;
    @BindView(R.id.fa1_12_iv) ImageView fa1_12_iv;
    @BindView(R.id.fa1_13_iv) ImageView fa1_13_iv;
    @BindView(R.id.fa1_14_iv) ImageView fa1_14_iv;
    @BindView(R.id.fa1_15_iv) ImageView fa1_15_iv;
    @BindView(R.id.fa1_16_iv) ImageView fa1_16_iv;
    @BindView(R.id.fa1_17_iv) ImageView fa1_17_iv;

    @BindView(R.id.faq1_expandableLayout) ExpandableLayout faq1_expandableLayout;
    @BindView(R.id.faq2_expandableLayout) ExpandableLayout faq2_expandableLayout;
    @BindView(R.id.faq3_expandableLayout) ExpandableLayout faq3_expandableLayout;
    @BindView(R.id.faq4_expandableLayout) ExpandableLayout faq4_expandableLayout;
    @BindView(R.id.faq5_expandableLayout) ExpandableLayout faq5_expandableLayout;
    @BindView(R.id.faq6_expandableLayout) ExpandableLayout faq6_expandableLayout;
    @BindView(R.id.faq7_expandableLayout) ExpandableLayout faq7_expandableLayout;
    @BindView(R.id.faq8_expandableLayout) ExpandableLayout faq8_expandableLayout;
    @BindView(R.id.faq9_expandableLayout) ExpandableLayout faq9_expandableLayout;
    @BindView(R.id.faq10_expandableLayout) ExpandableLayout faq10_expandableLayout;
    @BindView(R.id.faq11_expandableLayout) ExpandableLayout faq11_expandableLayout;
    @BindView(R.id.faq12_expandableLayout) ExpandableLayout faq12_expandableLayout;
    @BindView(R.id.faq13_expandableLayout) ExpandableLayout faq13_expandableLayout;
    @BindView(R.id.faq14_expandableLayout) ExpandableLayout faq14_expandableLayout;
    @BindView(R.id.faq15_expandableLayout) ExpandableLayout faq15_expandableLayout;
    @BindView(R.id.faq16_expandableLayout) ExpandableLayout faq16_expandableLayout;
    @BindView(R.id.faq17_expandableLayout) ExpandableLayout faq17_expandableLayout;

    @BindView(R.id.faq_1_quest) TextView faq_1_quest;
    @BindView(R.id.faq_2_quest) TextView faq_2_quest;
    @BindView(R.id.faq_3_quest) TextView faq_3_quest;
    @BindView(R.id.faq_4_quest) TextView faq_4_quest;
    @BindView(R.id.faq_5_quest) TextView faq_5_quest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        ButterKnife.bind(this);

        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        displayActionBar();
        ActionBar avy = getSupportActionBar();
        avy.setTitle(null);
        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        toolbar.setNavigationOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_from_left);
            }
        });

        if (toolbar_title != null) {
            toolbar_title.setText(getResources().getString(R.string.faq_title));
        }
    }


    @OnClick({R.id.faq_1_lyt, R.id.faq_2_lyt, R.id.faq_3_lyt, R.id.faq_4_lyt, R.id.faq_5_lyt,
            R.id.faq_6_lyt, R.id.faq_7_lyt, R.id.faq_8_lyt, R.id.faq_9_lyt, R.id.faq_10_lyt,
            R.id.faq_11_lyt, R.id.faq_12_lyt, R.id.faq_13_lyt, R.id.faq_14_lyt, R.id.faq_15_lyt,
            R.id.faq_16_lyt, R.id.faq_17_lyt})
    public void performOnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.faq_1_lyt:
                if (faq1_expandableLayout.isExpanded()) {
                    faq1_expandableLayout.collapse();
                    fa1_1_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq1_expandableLayout.expand();
                    fa1_1_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_2_lyt:
                if (faq2_expandableLayout.isExpanded()) {
                    faq2_expandableLayout.collapse();
                    fa1_2_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq2_expandableLayout.expand();
                    fa1_2_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_3_lyt:
                if (faq3_expandableLayout.isExpanded()) {
                    faq3_expandableLayout.collapse();
                    fa1_3_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq3_expandableLayout.expand();
                    fa1_3_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_4_lyt:
                if (faq4_expandableLayout.isExpanded()) {
                    faq4_expandableLayout.collapse();
                    fa1_4_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq4_expandableLayout.expand();
                    fa1_4_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_5_lyt:
                if (faq5_expandableLayout.isExpanded()) {
                    faq5_expandableLayout.collapse();
                    fa1_5_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq5_expandableLayout.expand();
                    fa1_5_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_6_lyt:
                if (faq6_expandableLayout.isExpanded()) {
                    faq6_expandableLayout.collapse();
                    fa1_6_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq6_expandableLayout.expand();
                    fa1_6_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_7_lyt:
                if (faq7_expandableLayout.isExpanded()) {
                    faq7_expandableLayout.collapse();
                    fa1_7_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq7_expandableLayout.expand();
                    fa1_7_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_8_lyt:
                if (faq8_expandableLayout.isExpanded()) {
                    faq8_expandableLayout.collapse();
                    fa1_8_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq8_expandableLayout.expand();
                    fa1_8_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_9_lyt:
                if (faq9_expandableLayout.isExpanded()) {
                    faq9_expandableLayout.collapse();
                    fa1_9_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq9_expandableLayout.expand();
                    fa1_9_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_10_lyt:
                if (faq10_expandableLayout.isExpanded()) {
                    faq10_expandableLayout.collapse();
                    fa1_10_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq10_expandableLayout.expand();
                    fa1_10_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_11_lyt:
                if (faq11_expandableLayout.isExpanded()) {
                    faq11_expandableLayout.collapse();
                    fa1_11_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq11_expandableLayout.expand();
                    fa1_11_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_12_lyt:
                if (faq12_expandableLayout.isExpanded()) {
                    faq12_expandableLayout.collapse();
                    fa1_12_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq12_expandableLayout.expand();
                    fa1_12_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_13_lyt:
                if (faq13_expandableLayout.isExpanded()) {
                    faq13_expandableLayout.collapse();
                    fa1_13_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq13_expandableLayout.expand();
                    fa1_13_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_14_lyt:
                if (faq14_expandableLayout.isExpanded()) {
                    faq14_expandableLayout.collapse();
                    fa1_14_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq14_expandableLayout.expand();
                    fa1_14_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_15_lyt:
                if (faq15_expandableLayout.isExpanded()) {
                    faq15_expandableLayout.collapse();
                    fa1_15_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq15_expandableLayout.expand();
                    fa1_15_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_16_lyt:
                if (faq16_expandableLayout.isExpanded()) {
                    faq16_expandableLayout.collapse();
                    fa1_16_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq16_expandableLayout.expand();
                    fa1_16_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.faq_17_lyt:
                if (faq17_expandableLayout.isExpanded()) {
                    faq17_expandableLayout.collapse();
                    fa1_17_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    faq17_expandableLayout.expand();
                    fa1_17_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            default:
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

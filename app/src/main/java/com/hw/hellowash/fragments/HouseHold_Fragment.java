package com.hw.hellowash.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hw.hellowash.Custom.ExpandableLayout;
import com.hw.hellowash.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HouseHold_Fragment extends Fragment
{
    private View rootView;

    @BindView(R.id.bedroom_lyt) LinearLayout bedroom_lyt;
    @BindView(R.id.home_furnishing_lyt) LinearLayout home_furnishing_lyt;
    @BindView(R.id.bathroom_lyt) LinearLayout bathroom_lyt;

    @BindView(R.id.bedroom_iv) ImageView bedroom_iv;
    @BindView(R.id.home_furnishing_iv) ImageView home_furnishing_iv;
    @BindView(R.id.bathroom_iv) ImageView bathroom_iv;

    @BindView(R.id.bedroom_expandableLayout) ExpandableLayout bedroom_expandableLayout;
    @BindView(R.id.home_furnishing_expandableLayout) ExpandableLayout home_furnishing_expandableLayout;
    @BindView(R.id.bathroom_expandableLayout) ExpandableLayout bathroom_expandableLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.household_fragment, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({R.id.bedroom_lyt, R.id.home_furnishing_lyt, R.id.bathroom_lyt})
    public void performClick(View view) {
        switch (view.getId()) {
            case R.id.bedroom_lyt:
                if (bedroom_expandableLayout.isExpanded()) {
                    bedroom_expandableLayout.collapse();
                    bedroom_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    bedroom_expandableLayout.expand();
                    bedroom_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.home_furnishing_lyt:
                if (home_furnishing_expandableLayout.isExpanded()) {
                    home_furnishing_expandableLayout.collapse();
                    home_furnishing_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    home_furnishing_expandableLayout.expand();
                    home_furnishing_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.bathroom_lyt:
                if (bathroom_expandableLayout.isExpanded()) {
                    bathroom_expandableLayout.collapse();
                    bathroom_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    bathroom_expandableLayout.expand();
                    bathroom_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;
        }
    }
}

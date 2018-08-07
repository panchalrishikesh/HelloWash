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

public class Women_Fragment extends Fragment
{
    private View rootView;

    @BindView(R.id.top_lyt) LinearLayout top_lyt;
    @BindView(R.id.bottom_lyt) LinearLayout bottom_lyt;
    @BindView(R.id.ethic_lyt) LinearLayout ethic_lyt;
    @BindView(R.id.dresses_lyt) LinearLayout dresses_lyt;

    @BindView(R.id.top_iv) ImageView top_iv;
    @BindView(R.id.bottom_iv) ImageView bottom_iv;
    @BindView(R.id.ethic_iv) ImageView ethic_iv;
    @BindView(R.id.dresses_iv) ImageView dresses_iv;

    @BindView(R.id.top_expandableLayout) ExpandableLayout top_expandableLayout;
    @BindView(R.id.bottom_expandableLayout) ExpandableLayout bottom_expandableLayout;
    @BindView(R.id.ethic_expandableLayout) ExpandableLayout ethic_expandableLayout;
    @BindView(R.id.dresses_expandableLayout) ExpandableLayout dresses_expandableLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.women_fragment, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({R.id.top_lyt, R.id.bottom_lyt, R.id.ethic_lyt, R.id.dresses_lyt})
    public void performClick(View view) {
        switch (view.getId()) {
            case R.id.top_lyt:
                if (top_expandableLayout.isExpanded()) {
                    top_expandableLayout.collapse();
                    top_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    top_expandableLayout.expand();
                    top_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.bottom_lyt:
                if (bottom_expandableLayout.isExpanded()) {
                    bottom_expandableLayout.collapse();
                    bottom_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    bottom_expandableLayout.expand();
                    bottom_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.ethic_lyt:
                if (ethic_expandableLayout.isExpanded()) {
                    ethic_expandableLayout.collapse();
                    ethic_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    ethic_expandableLayout.expand();
                    ethic_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;

            case R.id.dresses_lyt:
                if (dresses_expandableLayout.isExpanded()) {
                    dresses_expandableLayout.collapse();
                    dresses_iv.setImageResource(R.drawable.ic_down_arrow);
                } else {
                    dresses_expandableLayout.expand();
                    dresses_iv.setImageResource(R.drawable.ic_up_arrow);
                }
                break;
        }
    }
}

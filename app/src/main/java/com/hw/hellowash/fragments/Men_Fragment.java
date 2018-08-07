package com.hw.hellowash.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hw.hellowash.Custom.ExpandableLayout;
import com.hw.hellowash.R;
import com.hw.hellowash.activities.AddressTimeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Men_Fragment extends Fragment implements View.OnClickListener {
    private View rootView;

    @BindView(R.id.top_lyt)
    LinearLayout top_lyt;

    @BindView(R.id.top_iv)
    ImageView top_iv;

    @BindView(R.id.top_expandableLayout)
    ExpandableLayout top_expandableLayout;

    @BindView(R.id.bottom_lyt) LinearLayout bottom_lyt;

    @BindView(R.id.bottom_iv) ImageView bottom_iv;

    @BindView(R.id.bottom_expandableLayout)
    ExpandableLayout bottom_expandableLayout;

    @BindView(R.id.ethic_lyt) LinearLayout ethic_lyt;

    @BindView(R.id.ethic_iv)
    ImageView ethic_iv;

    @BindView(R.id.ethic_expandableLayout)
    ExpandableLayout ethic_expandableLayout;
    private TextView book_laundry_bottom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.men_fragment, container, false);
        ButterKnife.bind(this, rootView);

        findViews();
        return rootView;
    }

    private void findViews() {
        book_laundry_bottom = (TextView)getActivity().findViewById(R.id.book_laundry_bottom);
        book_laundry_bottom.setOnClickListener(this);
    }

    @OnClick({R.id.top_lyt, R.id.bottom_lyt, R.id.ethic_lyt})
    public void performClick(View view){
            switch (view.getId()){
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
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.book_laundry_bottom:
                Intent setpickupTimeIntent = new Intent(getActivity(), AddressTimeActivity.class);
                getActivity().startActivity(setpickupTimeIntent);
                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);
                break;
        }
    }
}

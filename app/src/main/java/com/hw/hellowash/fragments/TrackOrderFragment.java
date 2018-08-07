package com.hw.hellowash.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hw.hellowash.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrackOrderFragment extends Fragment {

    private View rootView;
    @BindView(R.id.place_order_btn)
    Button place_order_btn;
    private TextView toolbar_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.track_order_init_page, container, false);
        ButterKnife.bind(this, rootView);



        return rootView;
    }

    @OnClick(R.id.place_order_btn)
    public void navigateToPlaceOrderPage(){
        ((ViewPager)getActivity().findViewById(R.id.view_pager)).setCurrentItem(0);
    }
}

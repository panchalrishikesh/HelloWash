package com.hw.hellowash.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.hw.hellowash.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddsFragment extends Fragment {

    private View rootView;
    @BindView(R.id.image_view)
    ImageView image_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.adds_fragment, container, false);
        ButterKnife.bind(this, rootView);

        image_view.setImageResource(R.drawable.img1);

        return rootView;
    }
}

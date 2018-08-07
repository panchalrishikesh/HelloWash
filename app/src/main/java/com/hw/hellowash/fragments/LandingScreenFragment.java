package com.hw.hellowash.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hw.hellowash.Custom.AutoScrollViewPager;
import com.hw.hellowash.R;
import com.hw.hellowash.adapters.WashTypeSelectionAdapter;
import com.hw.hellowash.models.WashTypeSelection;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class LandingScreenFragment extends Fragment
{
    private View rootView;
    private TextView toolbar_title;
    int addCount = 4;
    private DiscreteScrollView discrete_view;
    WashTypeSelectionAdapter washTypeSelectionAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.landing_screen_fragment, container, false);
        ButterKnife.bind(this, rootView);

        setUpUI();
        toolbar_title = (TextView)getActivity().findViewById(R.id.toolbar_title);
        if(toolbar_title!=null){
            toolbar_title.setText(getResources().getString(R.string.hellowash));
        }

        AutoScrollViewPager viewPager = rootView.findViewById(R.id.view_pager);
        viewPager.setAdapter(new SamplePagerAdapter(getChildFragmentManager()));
        CircleIndicator indicator = (CircleIndicator) rootView.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        return rootView;
    }

    private void setUpUI() {

        List<WashTypeSelection> washTypeList = new ArrayList<>();

        WashTypeSelection mModel = new WashTypeSelection();
        mModel.setWashTypeImage(R.drawable.ic_shirt);
        mModel.setWashTypeName(getActivity().getResources().getString(R.string.wash_n_fold));

        WashTypeSelection mMode2 = new WashTypeSelection();
        mMode2.setWashTypeImage(R.drawable.ic_iron);
        mMode2.setWashTypeName(getActivity().getResources().getString(R.string.wash_n_iron));

        WashTypeSelection mMode3 = new WashTypeSelection();
        mMode3.setWashTypeImage(R.drawable.ic_iron_steam);
        mMode3.setWashTypeName(getActivity().getResources().getString(R.string.stean_iron));

        WashTypeSelection mMode4 = new WashTypeSelection();
        mMode4.setWashTypeImage(R.drawable.ic_dry_clean_new);
        mMode4.setWashTypeName(getActivity().getResources().getString(R.string.dry_clean));

        WashTypeSelection mMode5 = new WashTypeSelection();
        mMode5.setWashTypeImage(R.drawable.ic_dying);
        mMode5.setWashTypeName(getActivity().getResources().getString(R.string.dying));

        washTypeList.add(mModel);
        washTypeList.add(mMode2);
        washTypeList.add(mMode3);
        washTypeList.add(mMode4);
        washTypeList.add(mMode5);

        discrete_view = (DiscreteScrollView) rootView.findViewById(R.id.discrete_view);
        discrete_view.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                ((RelativeLayout)((LinearLayout)((RelativeLayout)viewHolder.itemView).getChildAt(0)).getChildAt(0)).setBackground(getActivity().getResources().getDrawable(R.drawable.circle_shape_red));
                ((TextView)((LinearLayout)((RelativeLayout)viewHolder.itemView).getChildAt(0)).getChildAt(1)).setVisibility(View.VISIBLE);
            }
        });

        discrete_view.addScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>() {
            @Override
            public void onScrollStart(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {
                ((RelativeLayout)((LinearLayout)((RelativeLayout)currentItemHolder.itemView).getChildAt(0)).getChildAt(0)).setBackground(getActivity().getResources().getDrawable(R.drawable.circle_shape_red));
                ((TextView)((LinearLayout)((RelativeLayout)currentItemHolder.itemView).getChildAt(0)).getChildAt(1)).setVisibility(View.INVISIBLE);
            }

            @Override
            public void onScrollEnd(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {
                ((RelativeLayout)((LinearLayout)((RelativeLayout)currentItemHolder.itemView).getChildAt(0)).getChildAt(0)).setBackground(getActivity().getResources().getDrawable(R.drawable.circle_shape_red));
                ((TextView)((LinearLayout)((RelativeLayout)currentItemHolder.itemView).getChildAt(0)).getChildAt(1)).setVisibility(View.INVISIBLE);
            }

            @Override
            public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrent) {

            }
        });

        washTypeSelectionAdapter = new WashTypeSelectionAdapter(getActivity(), washTypeList);
        discrete_view.setAdapter(washTypeSelectionAdapter);
        discrete_view.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
    }

    class SamplePagerAdapter extends FragmentStatePagerAdapter {

        SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new AddsFragment();
        }

        @Override
        public int getCount() {
            return addCount;
        }
    }
}

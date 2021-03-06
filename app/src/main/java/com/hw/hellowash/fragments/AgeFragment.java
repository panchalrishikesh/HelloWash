package com.hw.hellowash.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hw.hellowash.Custom.TypeWriter;
import com.hw.hellowash.R;
import com.hw.hellowash.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgeFragment extends Fragment
{
    @BindView(R.id.age_hint_text)
    TypeWriter age_hint_text;
    @BindView(R.id.age) EditText age;
    @BindView(R.id.age_next) FloatingActionButton age_next;

    private View rootView;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.age_fragment, container, false);
        ButterKnife.bind(this, rootView);

        age_hint_text.setText("");
        age_hint_text.setCharacterDelay(50);
        age_hint_text.animateText(getResources().getString(R.string.ask_age_text));

        return rootView;
    }

    @OnClick(R.id.age_next)
    public void cannNextFragment(){

        if(!TextUtils.isEmpty(age.getText().toString())) {
            Utilities.savePref(getActivity(), "Age", age.getText().toString());

            manager = getActivity().getSupportFragmentManager();

            AddressFragment address_Fragment = new AddressFragment();
            transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right_frag, R.anim.exit_to_left_frag, R.anim.enter_from_left_frag, R.anim.exit_to_right_frag);
            transaction.replace(R.id.fragment_container, address_Fragment);
            transaction.addToBackStack("AddressFragment");
            transaction.commit();

        }else{
            Utilities.showToast(getActivity(), "Please enter Age");
        }
    }
}

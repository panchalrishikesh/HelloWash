package com.hw.hellowash.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hw.hellowash.Custom.TypeWriter;
import com.hw.hellowash.R;
import com.hw.hellowash.Utilities;
import com.hw.hellowash.activities.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinalFragment extends Fragment
{
    @BindView(R.id.final_hint_text)
    TypeWriter final_hint_text;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.service_feq_text)
    TextView service_feq_text;
    @BindView(R.id.service_feq_lyt)
    RelativeLayout service_feq_lyt;

    private View rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.final_fragment, container, false);
        ButterKnife.bind(this, rootView);

        final_hint_text.setText("");
        final_hint_text.setCharacterDelay(50);
        final_hint_text.animateText(getResources().getString(R.string.allmost_done));

        return rootView;
    }


    @OnClick(R.id.submit)
    public void LandToMainScreen(){
        if(!service_feq_text.getText().toString().equalsIgnoreCase("") &&
                !service_feq_text.getText().toString().equalsIgnoreCase("How frequently you look for laundry?")) {

            Utilities.savebooleanPref(getActivity(), "HasLogged_In", true);
            Intent mainActivity_Intent = new Intent(getActivity(), MainActivity.class);
            getActivity().startActivity(mainActivity_Intent);
            getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_from_right);

        }else{
            Utilities.showToast(getActivity(), "Please select how frequently you look for laundry service.");
        }
    }

    @OnClick(R.id.service_feq_lyt)
    public void selectFrequence(){
        frequencyPopup();
    }

    private void frequencyPopup() {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
        builderSingle.setTitle("How frequently you look for laundry?");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_textview);
        arrayAdapter.clear();
        arrayAdapter.add("Once in a week");
        arrayAdapter.add("Twice in a week");
        arrayAdapter.add("Once in 10 days");
        arrayAdapter.add("Others");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String strName = arrayAdapter.getItem(which);
                service_feq_text.setText(strName);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builderSingle.create();
        ListView listView = dialog.getListView();
        listView.setVerticalScrollBarEnabled(false);
        listView.setDivider(new ColorDrawable(Color.parseColor("#44BDC8")));
        listView.setDividerHeight(1);
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
}

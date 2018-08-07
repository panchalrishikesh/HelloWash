package com.hw.hellowash.fragments;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.hw.hellowash.Custom.TypeWriter;
import com.hw.hellowash.R;
import com.hw.hellowash.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressFragment extends Fragment {
    @BindView(R.id.autoCompleteTextView) AutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.address_hint_text) TypeWriter address_hint_text;
    @BindView(R.id.address) EditText address;
    @BindView(R.id.landmark) EditText landmark;
    @BindView(R.id.address_next)
    FloatingActionButton address_next;
    @BindView(R.id.get_location)
    ImageView get_location;

    private View rootView;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Place place;
    private LatLng latlangObj;
    private Location fromLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.address_fragment, container, false);
        ButterKnife.bind(this, rootView);

        address_hint_text.setText("");
        address_hint_text.setCharacterDelay(50);
        address_hint_text.animateText(getResources().getString(R.string.ask_address_text));


        return rootView;
    }

    @OnClick(R.id.autoCompleteTextView)
    public void citySearch(){
        /*try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(getActivity());
            startActivityForResult(intent, 2);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
            e.printStackTrace();
        }*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                // retrive the data by using getPlace() method.
                place = PlaceAutocomplete.getPlace(getActivity(), data);

                latlangObj = place.getLatLng();
                Log.v("latitude:", "" + latlangObj.latitude);
                Log.v("longitude:", "" + latlangObj.longitude);

                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());

                fromLocation = new Location("from");//provider name is unecessary
                fromLocation.setLatitude(latlangObj.latitude);//your coords of course
                fromLocation.setLongitude(latlangObj.longitude);
                autoCompleteTextView.setText(place.getName());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @OnClick(R.id.get_location)
    public void setLocation(){
        address.setText(Utilities.getAddress(getActivity()));
        Utilities.savePref(getActivity(), "Address", Utilities.getAddress(getActivity()));
    }

    @OnClick(R.id.address_next)
    public void cannNextFragment(){

        if(!TextUtils.isEmpty(autoCompleteTextView.getText().toString()) &&
                !TextUtils.isEmpty(address.getText().toString()) &&
                !TextUtils.isEmpty(landmark.getText().toString())) {
            Utilities.savePref(getActivity(), "city", autoCompleteTextView.getText().toString());
            Utilities.savePref(getActivity(), "address", address.getText().toString());
            Utilities.savePref(getActivity(), "landmark", landmark.getText().toString());

            manager = getActivity().getSupportFragmentManager();

            FinalFragment final_Fragment = new FinalFragment();
            transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right_frag, R.anim.exit_to_left_frag, R.anim.enter_from_left_frag, R.anim.exit_to_right_frag);
            transaction.replace(R.id.fragment_container, final_Fragment);
            transaction.addToBackStack("FinalFragment");
            transaction.commit();

        }else{
            Utilities.showToast(getActivity(), "Please fill all fields");
        }
    }

}

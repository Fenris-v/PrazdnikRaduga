package com.petersburg_studio.prazdnikraduga.fragment.secondLevel;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.petersburg_studio.prazdnikraduga.R;

import java.util.Objects;

public class ContactsFragment extends Fragment implements OnMapReadyCallback {

    private LatLng location;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Objects.requireNonNull(getActivity()).setTitle(R.string.contacts);

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        location = new LatLng(55.70928275, 37.61436582);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(location)
                .title(getString(R.string.app_name_full)));
        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();
        latLngBuilder.include(location);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 15);
        googleMap.moveCamera(cameraUpdate);
    }
}

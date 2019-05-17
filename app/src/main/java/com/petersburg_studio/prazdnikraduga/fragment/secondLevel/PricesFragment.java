package com.petersburg_studio.prazdnikraduga.fragment.secondLevel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petersburg_studio.prazdnikraduga.R;

import java.util.Objects;

public class PricesFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Objects.requireNonNull(getActivity()).setTitle(R.string.price);

        return inflater.inflate(R.layout.fragment_prices, container, false);
    }
}
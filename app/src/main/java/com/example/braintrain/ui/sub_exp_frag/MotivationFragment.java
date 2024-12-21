package com.example.braintrain.ui.sub_exp_frag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.braintrain.R;

public class MotivationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (View) inflater.inflate(R.layout.fragment_motivation, container, false);
    }
}
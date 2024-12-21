package com.example.braintrain.ui.app_exp_dialog;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.braintrain.R;

public class ExplanationDiaFragment extends DialogFragment {

    public static ExplanationDiaFragment newInstance(String text) {
        ExplanationDiaFragment fragment = new ExplanationDiaFragment();
        Bundle args = new Bundle();
        args.putString("text", text);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_explanation_dia, container, false);

        TextView textView = view.findViewById(R.id.digText);

        if (getArguments() != null) {
            String text = getArguments().getString("text");
            textView.setText(text);
        }

        return view;
    }
}
package com.example.braintrain;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class WayImage extends Fragment {

    public static WayImage toFragmentImage(int position, String text) {
        WayImage fragmentImge = new WayImage();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("text", text);

        fragmentImge.setArguments(bundle);

        return fragmentImge;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_way_image, container, false);

        ImageView image = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);

        if (getArguments() != null) {
            int imgId = getArguments().getInt("position");
            String text = getArguments().getString("text");

            image.setImageResource(imgId);
            textView.setText(text);
        }

        return view;
    }
}
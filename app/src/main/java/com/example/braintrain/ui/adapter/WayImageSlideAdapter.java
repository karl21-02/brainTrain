package com.example.braintrain.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.braintrain.WayImage;

import java.util.ArrayList;

public class WayImageSlideAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Integer> images;
    private ArrayList<String> texts;

    public WayImageSlideAdapter(FragmentManager fm, ArrayList<Integer> images, ArrayList<String> texts) {
        super(fm);
        this.images = images;
        this.texts = texts;
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return WayImage.toFragmentImage(images.get(position), texts.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "home";
        }
        else if(position == 1) {
            return "way";
        }
        else if(position == 2) {
            return "hanbit";
        }
        else if(position == 3) {
            return "playground";
        }
        else if(position == 4){
            return "mirae";
        }
        else  {
            return "destination";
        }
    }
}

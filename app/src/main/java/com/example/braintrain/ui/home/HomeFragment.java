package com.example.braintrain.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.braintrain.R;
import com.example.braintrain.databinding.FragmentHomeBinding;
import com.example.braintrain.ui.Entity.HomeItem;
import com.example.braintrain.ui.adapter.HomeItemAdapter;
import com.example.braintrain.ui.adapter.SubExplanationItemsAdapter;
import com.example.braintrain.ui.app_exp_dialog.ExplanationDiaFragment;
import com.example.braintrain.ui.adapter.WayImageSlideAdapter;
import com.example.braintrain.ui.sub_exp_frag.FunctionFragment;
import com.example.braintrain.ui.sub_exp_frag.IntroduceFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<String> texts = new ArrayList<>();
    ArrayList<HomeItem> homeItems = new ArrayList<>();

    ViewPager viewPager;
    int curPosition;
    TabLayout tabLayout;
    TextView textView;

    RecyclerView recyclerView;
    FrameLayout containerView;
    SubExplanationItemsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(images.isEmpty()) {
            images.add(getResources().getIdentifier("myhome", "drawable", requireContext().getPackageName()));
            images.add(getResources().getIdentifier("way", "drawable", getContext().getPackageName()));
            images.add(getResources().getIdentifier("hanbit", "drawable", getContext().getPackageName()));
            images.add(getResources().getIdentifier("playground", "drawable", getContext().getPackageName()));
            images.add(getResources().getIdentifier("mirae", "drawable", getContext().getPackageName()));
            images.add(getResources().getIdentifier("destination", "drawable", getContext().getPackageName()));
        }

        if(texts.isEmpty()) {
            texts.add("Welcome to My Home");
            texts.add("This is the Way");
            texts.add("Hanbit Building");
            texts.add("Playground");
            texts.add("Mirae Library");
            texts.add("Final Destination");
        }

        if(homeItems.isEmpty()) {
            homeItems.add(new HomeItem("소개"));
            homeItems.add(new HomeItem("기능"));
            homeItems.add(new HomeItem("사용법"));
            homeItems.add(new HomeItem("동기"));
        }

        if(savedInstanceState != null) {
            curPosition = savedInstanceState.getInt("position");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = FragmentHomeBinding.inflate(inflater, container, false).getRoot();
        viewPager = root.findViewById(R.id.pager);
        tabLayout = root.findViewById(R.id.tabLayout);
        textView = root.findViewById(R.id.textView13);
        recyclerView = root.findViewById(R.id.home_items_recycler);
        containerView = root.findViewById(R.id.view4);




        textView.setOnClickListener(v -> {
            String text = "시험공부를 하려면 원룸에서 미래의 도서관까지 걸어가야 합니다. 하지만, 학점을 잘 받기 위해서 방금 일어난 준희가 미래 도서관에 도착하자마자 공부하려면 가는 시간 동안 뇌를 단련해야한다!";
            ExplanationDiaFragment dig = ExplanationDiaFragment.newInstance(text);
            dig.show(getParentFragmentManager(), "DetailDialog");
        });

        if(viewPager.getAdapter() == null) {
            WayImageSlideAdapter imageSlideAdapter = new WayImageSlideAdapter(getChildFragmentManager(), images, texts);

            HomeItemAdapter homeItemAdapter = new HomeItemAdapter(homeItems, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new SubExplanationItemsAdapter(getContext(), containerView);
            recyclerView.setAdapter(homeItemAdapter);

            viewPager.setAdapter(imageSlideAdapter);
            viewPager.setCurrentItem(curPosition);

            tabLayout.setupWithViewPager(viewPager);

            viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {

                @Override
                public void transformPage(@NonNull View view, float position) {
                    if (position <= 1) {
                        float scaleFactor = 0.3f + 0.7f * (1 - Math.abs(position));
                        view.setScaleX(scaleFactor);
                        view.setScaleY(scaleFactor);
                        view.setTranslationZ(-Math.abs(position) * 5);
                    }
                }
            });

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

                @Override
                public void onPageSelected(int position) {
                    curPosition = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {}
            });
        }

        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.beginTransaction().replace(containerView.getId(), new IntroduceFragment()).commit();
        }

        return root;
    }

    public void replaceFragment(Fragment fragment) {
        if (fragment != null && !fragment.isAdded()) {
            getChildFragmentManager().beginTransaction().replace(R.id.view4, fragment).addToBackStack(null).commit();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", curPosition);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
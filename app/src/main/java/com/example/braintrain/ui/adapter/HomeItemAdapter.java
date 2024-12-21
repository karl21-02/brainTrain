package com.example.braintrain.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrain.R;
import com.example.braintrain.ui.Entity.HomeItem;
import com.example.braintrain.ui.home.HomeFragment;
import com.example.braintrain.ui.sub_exp_frag.FunctionFragment;
import com.example.braintrain.ui.sub_exp_frag.HowFragment;
import com.example.braintrain.ui.sub_exp_frag.IntroduceFragment;
import com.example.braintrain.ui.sub_exp_frag.MotivationFragment;

import java.util.List;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> {

    List<HomeItem> list;
    HomeFragment homeFragment;

    public HomeItemAdapter(List<HomeItem> list, HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.home_item_text);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeItem homeItem = list.get(position); // 내가 보고있는 position의 HomeItem에서 제목 가져와서 비교 후 적절한 프레그 먼트로 교체
        holder.text.setText(homeItem.getTitle());

        holder.itemView.setOnClickListener(v -> {
            if (homeItem.getTitle().equals("소개")) {
                homeFragment.replaceFragment(new IntroduceFragment());
            }
            else if (homeItem.getTitle().equals("기능")) {
                homeFragment.replaceFragment(new FunctionFragment());
            }
            else if (homeItem.getTitle().equals("사용법")) {
                homeFragment.replaceFragment(new HowFragment());
            }
            else if (homeItem.getTitle().equals("동기")) {
                homeFragment.replaceFragment(new MotivationFragment());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}

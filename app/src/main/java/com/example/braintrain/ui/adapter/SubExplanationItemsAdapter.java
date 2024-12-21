package com.example.braintrain.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrain.R;
import com.example.braintrain.ui.sub_exp_frag.FunctionFragment;
import com.example.braintrain.ui.sub_exp_frag.HowFragment;
import com.example.braintrain.ui.sub_exp_frag.IntroduceFragment;
import com.example.braintrain.ui.sub_exp_frag.MotivationFragment;


public class SubExplanationItemsAdapter extends RecyclerView.Adapter<SubExplanationItemsAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.home_item_text);
        }
    }

    String[] items = {"기능", "사용법", "소개", "동기"};
    Context context;
    FrameLayout containerView;

    public SubExplanationItemsAdapter(Context context, FrameLayout containerView) {
        this.context = context;
        this.containerView = containerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(items[position]);
        holder.itemView.setOnClickListener(v -> {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = new FunctionFragment();
                    break;
                case 1:
                    fragment = new HowFragment();
                    break;
                case 2:
                    fragment = new IntroduceFragment();
                    break;
                case 3:
                    fragment = new MotivationFragment();
                    break;
                default:
                    return;
            }
            showFragment(fragment);
        });
    }

    public void showFragment(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.replace(containerView.getId(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}

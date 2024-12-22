package com.example.braintrain.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrain.R;
import com.example.braintrain.ui.Entity.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ResultAdapter extends  RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private ArrayList<Result> results = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_result, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position < 0 || position >= results.size()) return;
        Result result = results.get(position);
        holder.setItem(result);

        holder.imageView.setOnClickListener(v -> {
            int position1 = holder.getAdapterPosition();

            if(position1 != RecyclerView.NO_POSITION) {
                results.remove(position1);
                notifyItemRemoved(position1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void addResult(Result result) {
        results.add(result);
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public Result getResult(int position) {
        if (position < 0 || position >= results.size()) {
            return null;
        }
        return results.get(position);
    }

    public void setResult(int position, Result result) {
        if (position < 0 || position >= results.size()) {
            return;
        }
        results.set(position, result);
        notifyItemChanged(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView1;
        TextView textView2;
        ImageView imageView;
        TextView username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView5);
            textView1 = itemView.findViewById(R.id.textView7);
            textView2 = itemView.findViewById(R.id.textView27);
            imageView = itemView.findViewById(R.id.imageView9);
            username = itemView.findViewById(R.id.username);

            itemView.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "pos= " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            });
        }

        public void setItem(Result result) {

            textView.setText("LEVEL " + result.getLevel());
            textView1.setText("SCORE " + result.getScore());
            textView2.setText("SUCCESS TIME " + result.getTime());
            username.setText(result.getUserName());
        }
    }
}

package com.example.braintrain.ui.resultAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrain.R;
import com.example.braintrain.ui.Entity.Result;

import java.util.ArrayList;

public class ResultAdapter extends  RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    ArrayList<Result> results = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_result, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = results.get(position);
        holder.setItem(result);
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
    }

    public Result getResult(int position) {
        return results.get(position);
    }

    public void setItem(int position, Result result) {
        results.set(position, result);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView5);
            textView1 = itemView.findViewById(R.id.textView7);

        }

        public void setItem(Result result) {
            textView.setText(result.getName());
            textView1.setText(result.getMobile());
        }


    }
}

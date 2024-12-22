package com.example.braintrain.ui.result;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrain.PreferenceManager;
import com.example.braintrain.R;
import com.example.braintrain.ui.Entity.Result;
import com.example.braintrain.ui.adapter.ResultAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ResultFragment extends Fragment {

    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private TextView motivationTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view =(ViewGroup) inflater.inflate(R.layout.fragment_result, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        motivationTextView = view.findViewById(R.id.textView28);

        adapter = new ResultAdapter();
        recyclerView.setAdapter(adapter);

        ArrayList<Result> results = ResultSingleton.getInstance().getResults();
        for(Result result : results) {
            adapter.addResult(result);
        }
        adapter.notifyDataSetChanged();

//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));
//        adapter.addResult(new Result(1, 2, "userName"));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        if(bundle != null && !bundle.isEmpty()) {
            String receivedData = bundle.getString("key", "No Data");
            motivationTextView.setText(receivedData);
        }
    }

}
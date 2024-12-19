package com.example.braintrain.ui.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.braintrain.R;
import com.example.braintrain.ui.Entity.Result;
import com.example.braintrain.ui.resultAdapter.ResultAdapter;

public class ResultFragment extends Fragment {

    private RecyclerView recyclerView;
    private ResultAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view =(ViewGroup) inflater.inflate(R.layout.fragment_result, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        adapter = new ResultAdapter();
        recyclerView.setAdapter(adapter);

        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("name")) {
            String name = bundle.getString("name");
            adapter.addResult(new Result(name, "game done!"));
            adapter.notifyDataSetChanged();
        }

        return view;
    }
}
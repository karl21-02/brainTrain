package com.example.braintrain.ui.result;

import com.example.braintrain.ui.Entity.Result;

import java.util.ArrayList;

public class ResultSingleton {
    private static ResultSingleton instance;
    private final ArrayList<Result> results;

    private ResultSingleton() {
        results = new ArrayList<>();
    }

    public static ResultSingleton getInstance() {
        if (instance == null) {
            instance = new ResultSingleton();
        }
        return instance;
    }

    public void addResult(Result result) {
        results.add(result);
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void clearResults() {
        results.clear();
    }
}

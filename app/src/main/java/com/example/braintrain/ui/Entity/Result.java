package com.example.braintrain.ui.Entity;

public class Result {
    int level;
    int score;
    String userName;

    public Result(int level, int score, String userName) {
        this.level = level;
        this.score = score;
        this.userName = userName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

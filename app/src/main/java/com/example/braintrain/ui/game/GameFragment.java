package com.example.braintrain.ui.game;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.braintrain.R;

import java.util.ArrayList;
import java.util.Collections;

public class GameFragment extends Fragment {

    TextView textView;

    TextView timer;
    TextView score;
    TextView level;

    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;
    TextView text9;

    boolean time_end_exit = true;

    ArrayList<Integer> currentGameData;
    static int currentClickCount = 0;
    static int currentScore = 0;
    static int currentLevel = 1;
    static int count_touch_num=0;
    static boolean[] isCorrect;

    LinearLayout game1;
    LinearLayout game2;
    LinearLayout game3;
    LinearLayout game4;
    LinearLayout game5;
    LinearLayout game6;
    LinearLayout game7;
    LinearLayout game8;
    LinearLayout game9;

    boolean isStarted;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view =(ViewGroup) inflater.inflate(R.layout.fragment_game, container, false);

        textView = view.findViewById(R.id.textView6);

        timer = view.findViewById(R.id.textView4);
        score = view.findViewById(R.id.textView50);
        level = view.findViewById(R.id.textView51);

        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
        text3 = view.findViewById(R.id.text3);
        text4 = view.findViewById(R.id.text4);
        text5 = view.findViewById(R.id.text5);
        text6 = view.findViewById(R.id.text6);
        text7 = view.findViewById(R.id.text7);
        text8 = view.findViewById(R.id.text8);
        text9 = view.findViewById(R.id.text9);

        ///////////
        game1 = view.findViewById(R.id.game1);
        game2 = view.findViewById(R.id.game2);
        game3 = view.findViewById(R.id.game3);
        game4 = view.findViewById(R.id.game4);
        game5 = view.findViewById(R.id.game5);
        game6 = view.findViewById(R.id.game6);
        game7 = view.findViewById(R.id.game7);
        game8 = view.findViewById(R.id.game8);
        game9 = view.findViewById(R.id.game9);



        ///////////

        currentClickCount = 0;
        currentScore = 0;
        currentLevel = 1;
        count_touch_num=0;

        textView.setOnClickListener(v -> {

            if(time_end_exit) {
                count_touch_num=0;
                time_end_exit = false;
                renew_timer();


                // 게임 시작-----------
                isStarted = true;
                try {
                    boolean isSuccess = startGame();
                    isCorrect = new boolean[9];

                    for(int i=0; i<9; i++) {
                        int resId = getResources().getIdentifier("game" + (i + 1), "id", getContext().getPackageName());
                        LinearLayout game = view.findViewById(resId);
                        setBoxBackgroundColor(game, "#ffdab9");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(isStarted) {
                    check_user_click(isCorrect, currentGameData);
                }
            }
            else {
                Toast.makeText(requireContext(), "Please Wait for timeout !", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void handleBoxClick(int index, LinearLayout game) {
        count_touch_num++;
        Toast.makeText(requireContext(), index + " Clicked", Toast.LENGTH_SHORT).show();
        if(count_touch_num != currentGameData.get(index-1)) {
            // 게임 종료
            Toast.makeText(requireContext(), "GAME OVER\nPlease Start Again", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!isStarted) {
            Toast.makeText(requireContext(), "Please Start Game!", Toast.LENGTH_SHORT).show();
            return;
        }
        isCorrect[currentGameData.get(index-1)-1] = true;

        setBoxBackgroundColor(game, "#F08080");

        if(is_user_click_answers(isCorrect)) {
            Toast.makeText(requireContext(), "level " + currentLevel + " success", Toast.LENGTH_LONG).show();
            currentLevel = update_level(currentLevel);
            currentScore = update_score(currentScore);
            count_touch_num = 0;
            show_current_infos(currentLevel, currentScore);
            
        }
    }

    public void setBoxBackgroundColor(LinearLayout game, String colorCode) {
        game.setBackgroundColor(Color.parseColor(colorCode));
    }

    
    public void renew_timer() {
        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long l) {
                timer.setText("TIMER\n" + (l / 1000));
                time_end_exit = false;
            }

            @Override
            public void onFinish() {
                timer.setText("TIMER\n0");
                time_end_exit = true;
            }
        }.start();

    }
    public boolean startGame() throws InterruptedException {
        new Handler(requireContext().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (time_end_exit || currentLevel > 3) return;

                show_current_infos(currentLevel, currentScore);

                new Handler(requireContext().getMainLooper()).postDelayed(() -> {
                    if (!time_end_exit) {
                        ArrayList<Integer> list = createGameData();
                        try {
                            show_results(list, currentLevel);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        currentGameData = list;
                    }
                }, 0);
            }
        });

        return true;
    }

    private void show_current_infos(int level, int score) {
        this.score.setText("SCORE\n" + level);
        this.level.setText("LEVEL\n" + score);
    }

    public boolean is_user_click_answers(boolean[] isCorrect) {
        for(int i=0; i<isCorrect.length; i++) {
            if(!isCorrect[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean[] check_user_click(boolean[] isCorrect, ArrayList<Integer> list) {
        text1.setOnClickListener(v -> {
            handleBoxClick(1, game1);
        });
        text2.setOnClickListener(v -> {
            handleBoxClick(2, game2);

        });
        text3.setOnClickListener(v -> {
            handleBoxClick(3, game3);

        });
        text4.setOnClickListener(v -> {
            handleBoxClick(4, game4);

        });
        text5.setOnClickListener(v -> {
            handleBoxClick(5, game5);

        });
        text6.setOnClickListener(v -> {
            handleBoxClick(6, game6);

        });
        text7.setOnClickListener(v -> {
            handleBoxClick(7, game7);

        });
        text8.setOnClickListener(v -> {
            handleBoxClick(8, game8);

        });
        text9.setOnClickListener(v -> {
            handleBoxClick(9, game9);

        });
        return isCorrect;
    }

    public void show_results(ArrayList<Integer> list, int level) throws InterruptedException {
        text1.setText(String.valueOf(list.get(0)));
        text2.setText(String.valueOf(list.get(1)));
        text3.setText(String.valueOf(list.get(2)));
        text4.setText(String.valueOf(list.get(3)));
        text5.setText(String.valueOf(list.get(4)));
        text6.setText(String.valueOf(list.get(5)));
        text7.setText(String.valueOf(list.get(6)));
        text8.setText(String.valueOf(list.get(7)));
        text9.setText(String.valueOf(list.get(8)));

        int delayTime = 0;
        if(level == 1) {
            delayTime = 4000;
        }
        else if(level == 2) {
            delayTime = 3000;
        }
        else {
            delayTime = 2000;
        }

        new Handler(requireContext().getMainLooper()).postDelayed(() -> {
            if(!time_end_exit) {
                text1.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");
                text5.setText("");
                text6.setText("");
                text7.setText("");
                text8.setText("");
                text9.setText("");
                currentClickCount = 0; // 클릭 카운트 초기화
                currentGameData = list; // 현재 게임 데이터 저장
            }
        }, delayTime);
    }

    public int update_level(int level) {
        return level + 1;
    }

    public int update_score(int score) {
        return score + 1;
    }

    private ArrayList<Integer> createGameData() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        Collections.shuffle(list);
        return list;
    }
}
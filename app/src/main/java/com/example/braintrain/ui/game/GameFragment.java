package com.example.braintrain.ui.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.braintrain.R;
import com.example.braintrain.ui.Entity.Result;
import com.example.braintrain.ui.result.ResultFragment;
import com.example.braintrain.ui.result.ResultSingleton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GameFragment extends Fragment {

    TextView textView;
    TextView textView8;

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

    static String userName;
    static boolean check_user_input;

    ViewGroup view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view =(ViewGroup) inflater.inflate(R.layout.fragment_game, container, false);

        textView = view.findViewById(R.id.textView6);
        textView8 = view.findViewById(R.id.textView8);

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

            if(!check_user_input) { // 사용자 이름 입력받고 겜 시작
                getUserName();
                check_user_input = true;
            }
            else { // 사용자 이름 입력 받았으면 그냥 진행
                startGameWithUserName();
            }
        });

        if (savedInstanceState != null) {
            currentLevel = savedInstanceState.getInt("currentLevel", 1);
            currentScore = savedInstanceState.getInt("currentScore", 0);
        }

        score.setText("SCORE\n" + currentScore);
        level.setText("LEVEL\n" + currentLevel);
        textView8.setText(userName);

        return view;
    }

    public void startGameWithUserName() {
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
    }

    @Override
    public void onResume() {
        super.onResume();
        score.setText("SCORE\n" + currentScore);
        level.setText("LEVEL\n" + currentLevel);
        textView8.setText(userName);
    }

    public boolean getUserName() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_input_name, null);
        alertDialog.setView(dialogView);

        EditText editTextName = dialogView.findViewById(R.id.editTextName);

        alertDialog.setTitle("Enter Your Name");
        alertDialog.setPositiveButton("OK", (dialog, which) -> {
            userName = editTextName.getText().toString();

            TextView textView8 = getView().findViewById(R.id.textView8);
            if(userName == null || userName.equals("")) {
                textView8.setText("KIM JUN HEE(.dev)");
            }
            else {
                textView8.setText(userName);
            }
            startGameWithUserName();
        });

        AlertDialog dialog = alertDialog.create();
        dialog.setCancelable(false);
        dialog.show();

        return true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentLevel", currentLevel);
        outState.putInt("currentScore", currentScore);
    }

    private void handleBoxClick(int index, LinearLayout game) {
        count_touch_num++;
        Toast.makeText(requireContext(), index + " Clicked", Toast.LENGTH_SHORT).show();
        if(count_touch_num != currentGameData.get(index-1)) {
            Toast.makeText(requireContext(), "GAME OVER\nPlease Start Again", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!isStarted) {
            Toast.makeText(requireContext(), "Please Start Game!", Toast.LENGTH_SHORT).show();
            return;
        }
        isCorrect[currentGameData.get(index-1)-1] = true;

        setBoxBackgroundPic(game);

        if(is_user_click_answers(isCorrect)) {
            Toast.makeText(requireContext(), "level " + currentLevel + " success", Toast.LENGTH_LONG).show();
            currentLevel = update_level(currentLevel);
            currentScore = update_score(currentScore);
            count_touch_num = 0;
            show_current_infos(currentLevel, currentScore);
            if(currentLevel >= 3) {
                gameDoneAndAddResult();
            }
            
        }
    }

    public void setBoxBackgroundColor(LinearLayout game, String colorCode) {
        game.setBackgroundColor(Color.parseColor(colorCode));
    }

    public void setBoxBackgroundPic(LinearLayout game) {
        game.setBackgroundResource(R.drawable.card);
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

    public void gameDoneAndAddResult() {

        isStarted = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String curTime = sdf.format(new Date());

        Result result = new Result(currentLevel, currentScore, userName, curTime);
        ResultSingleton.getInstance().addResult(result);

        currentClickCount = 0;
        currentScore = 0;
        currentLevel = 1;
        count_touch_num=0;

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_slideshow);
    }

    public boolean startGame() throws InterruptedException {
        new Handler(requireContext().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (time_end_exit) return;

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
        return (score + 1) * currentLevel;
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
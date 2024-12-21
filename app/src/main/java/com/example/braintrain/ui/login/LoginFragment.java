package com.example.braintrain.ui.login;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.braintrain.LoginActivity;
import com.example.braintrain.MainActivity;
import com.example.braintrain.R;
import com.example.braintrain.data.DataBaseHelper;

public class LoginFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;
    private ProgressBar loadingProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEditText = view.findViewById(R.id.username);
        passwordEditText = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.login);
        signUpButton = view.findViewById(R.id.button);
        loadingProgressBar = view.findViewById(R.id.loading);
        createDatabase();

        loginButton.setOnClickListener(v -> {
            loadingProgressBar.setVisibility(View.VISIBLE);
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            doLogin(email, password);
        });

        signUpButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if(!email.equals("") && email != null && !password.equals("") && password != null) {
                doSignUp(email, password);
            }
        });
    }

    DataBaseHelper dbHelper;
    SQLiteDatabase database;

    private void createDatabase() {
        dbHelper = new DataBaseHelper(getContext());
        database = dbHelper.getWritableDatabase();
    }

    private void doSignUp(String email, String password) {
        Cursor cursor = null;
        if(
                password.length() >= 8 && email.matches("^[a-zA-Z0-9]+@.+\\..+$")

        ) {
            cursor = database.rawQuery("SELECT * FROM user WHERE email = ?", new String[]{email});

            if(cursor.getCount() > 0) {
                Toast.makeText(getContext(), "이미 존재하는 이메일입니다.", Toast.LENGTH_SHORT).show();
            }
            else {
                database.execSQL("INSERT INTO user (email, password) VALUES (?, ?)", new String[]{email, password});
                Toast.makeText(getContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getContext(), "이메일 형식 혹은 비밀번호 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private void doLogin(String email, String password) {
        new Thread(() -> {
            Cursor cursor = database.rawQuery("SELECT * FROM user WHERE email = ? AND password = ?", new String[]{email, password});
            Handler handler = new Handler(getContext().getMainLooper());

            boolean isLoginSuccessful;
            if (cursor.getCount() > 0) {
                isLoginSuccessful = true;
            } else {
                isLoginSuccessful = false;
            }

            handler.post(() -> {
                if (isLoginSuccessful) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                    loadingProgressBar.setVisibility(View.GONE);
                }
                cursor.close();
            });
        }).start();
    }
}

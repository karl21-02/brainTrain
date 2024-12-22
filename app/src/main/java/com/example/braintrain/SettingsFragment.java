package com.example.braintrain;

import static com.example.braintrain.ui.login.LoginFragment.database;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.braintrain.ui.login.LoginActivity;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        EditTextPreference preferenceSignature = findPreference("signature");
        Preference logout = findPreference("logout");
        Preference signout = findPreference("signout");

        if (preferenceSignature != null) {
            preferenceSignature.setSummaryProvider(null);

            String input = preferenceSignature.getText();
            if (input == null || input.isEmpty()) {
                preferenceSignature.setSummary(getString(R.string.select_and_enter));
            } else {
                preferenceSignature.setSummary(input);
            }

            preferenceSignature.setOnPreferenceChangeListener((preference, newValue) -> {
                preference.setSummary((String) newValue);
                Bundle bundle = new Bundle();
                bundle.putString("key", (String) newValue);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_slideshow, bundle);
                return true;
            });
        }

        if(logout != null) {
            logout.setOnPreferenceClickListener(preference -> {
                logout();
                return true;
            });
        }
        if(signout != null) {
            signout.setOnPreferenceClickListener(preference -> {
                signout();
                return true;
            });
        }
    }

    public void signout() {
        Toast.makeText(getContext(), "회원 탈퇴 하셨습니다.", Toast.LENGTH_SHORT).show();

        String email = getLoggedInUserEmail();
        if (email != null) {
            database.delete("user", "email = ?", new String[]{email});
        }
        getContext().getSharedPreferences("user_prefs", getContext().MODE_PRIVATE).edit().remove("logged_in_email").apply();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    private String getLoggedInUserEmail() {
        return getContext().getSharedPreferences("userInfo", getContext().MODE_PRIVATE).getString("email", null);
    }

    public void logout() {
        Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
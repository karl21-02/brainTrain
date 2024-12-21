package com.example.braintrain;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.braintrain.ui.result.ResultFragment;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        EditTextPreference preferenceSignature = findPreference("signature");

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
//                sendInputToResultFrag((String) newValue);
                Bundle bundle = new Bundle();
                bundle.putString("key", (String) newValue);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_slideshow, bundle);
                return true;
            });
        }
    }

    private void sendInputToResultFrag(String newValue) {
        PreferenceManager.saveMotivation(requireContext(), newValue);
    }
}
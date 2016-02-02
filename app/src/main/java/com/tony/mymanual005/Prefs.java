package com.tony.mymanual005;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;



public class Prefs extends AppCompatActivity {

    public static final String APP_PREFERENCES = "settings";
    public static final String APP_PREFERENCES_TEXT_SIZE = "text_size";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.settings);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        int fSize = Integer.parseInt(mSettings.getString(
                getString(R.string.key_text_size), "20"));

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_TEXT_SIZE, fSize);
        editor.apply();
    }
}

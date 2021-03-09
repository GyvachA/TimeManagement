package com.management.timemanagement.ui.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.SharedPreferencesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.management.timemanagement.R;
import com.management.timemanagement.data.local.SessionManager;
import com.management.timemanagement.ui.answers.QAActivity;
import com.management.timemanagement.ui.authorization.LoginActivity;
import com.management.timemanagement.ui.project_add.AddProjectActivity;

public class SettingsFragment extends Fragment {

    public static class SetFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preference, rootKey);
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        getParentFragmentManager().beginTransaction()
                .add(R.id.prefs_content, new SetFragment())
                .commit();
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar_settings, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit_action:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                new SessionManager(getContext()).setLogin(false);
                new SessionManager(getContext()).setNickname("");
                getActivity().finish();
                return true;
            case R.id.question_action:
                Intent i = new Intent(getActivity(), QAActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

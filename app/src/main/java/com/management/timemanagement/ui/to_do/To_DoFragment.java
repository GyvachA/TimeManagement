package com.management.timemanagement.ui.to_do;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.management.timemanagement.R;

public class To_DoFragment extends Fragment {

    private To_DoViewModel toDoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        toDoViewModel = ViewModelProviders.of(this).get(To_DoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_to_do, container, false);
        final TextView textView = root.findViewById(R.id.textView);
        toDoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar_menu, menu);

        MenuItem add_btn = menu.getItem(R.id.add_task);

        add_btn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ////////////////////////////////
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}

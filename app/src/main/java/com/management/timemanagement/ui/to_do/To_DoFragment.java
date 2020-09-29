package com.management.timemanagement.ui.to_do;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.ui.task_add.AddActivity;
import com.management.timemanagement.ui.task_recyclerview.TasksAdapter;
import com.management.timemanagement.utils.Task;

import java.util.ArrayList;

public class To_DoFragment extends Fragment {

    private RecyclerView tasks_rv;
    private TasksAdapter adapter;
    private ArrayList<Task> tasks_list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        
        To_DoViewModel toDoViewModel = new ViewModelProvider(this).get(To_DoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_to_do, container, false);

        tasks_rv = root.findViewById(R.id.task_list_rv);
        tasks_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TasksAdapter(tasks_list);
        refresh();
        tasks_rv.setAdapter(adapter);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_task:
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void refresh() {
        DBAdapter db = new DBAdapter(getContext());
        db.openDB();

        tasks_list.clear();

        Cursor cursor = db.getTaskDetailsNotReady();


        while(cursor.moveToNext()) {
            tasks_list.add(new Task(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)));
        }

        db.closeDB();
    }
}

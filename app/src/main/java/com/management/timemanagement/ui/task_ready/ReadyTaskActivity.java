package com.management.timemanagement.ui.task_ready;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.ui.task_recyclerview.TasksAdapter;
import com.management.timemanagement.ui.task_recyclerview.TasksReadyAdapter;
import com.management.timemanagement.utils.Task;

import java.util.ArrayList;

public class ReadyTaskActivity extends AppCompatActivity {

    private RecyclerView tasks_rv;
    private TasksReadyAdapter adapter;
    private ArrayList<Task> tasks_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Готовые задачи");
        setContentView(R.layout.activity_ready_task);

        refresh();


        tasks_rv = findViewById(R.id.task_list_ready_rv);
        tasks_rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TasksReadyAdapter(tasks_list);

        tasks_rv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void refresh() {
        DBAdapter db = new DBAdapter(this);
        db.openDB();

        tasks_list.clear();

        Cursor cursor = db.getTaskDetailsReady();


        while(cursor.moveToNext()) {
            tasks_list.add(new Task(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)));
        }

        db.closeDB();
    }
}
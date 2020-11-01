package com.management.timemanagement.ui.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.ui.project_rv.ProjectTaskAdapter;
import com.management.timemanagement.ui.task_recyclerview.TasksReadyAdapter;
import com.management.timemanagement.utils.Task;

import java.util.ArrayList;
import java.util.List;

public class ProjectTaskActivity extends AppCompatActivity {
    private RecyclerView tasks_rv;
    private ProjectTaskAdapter adapter;
    private List<Task> tasks_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_task);

        setTitle(getIntent().getStringExtra("name"));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        refresh();

        tasks_rv = findViewById(R.id.project_task_list_rv);
        tasks_rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectTaskAdapter(tasks_list);

        tasks_rv.setAdapter(adapter);
    }

    private void refresh() {
        DBAdapter db = new DBAdapter(this);
        db.openDB();

        tasks_list.clear();

        Cursor cursor = db.getProjectTaskDetails(getIntent().getIntExtra("id", -1));


        while(cursor.moveToNext()) {
            tasks_list.add(new Task(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)));
        }

        db.closeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_project, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(Activity.RESULT_OK);
                this.finish();
                return true;
            case R.id.add_project:
                Intent i = new Intent(this, ProjectTaskAddActivity.class);
                i.putExtra("project_id", getIntent().getIntExtra("id", -1));
                startActivityForResult(i, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            refresh();
            adapter.notifyDataSetChanged();
        }
    }
}
package com.management.timemanagement.ui.task_add;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.utils.Task;

public class EditTaskActivity extends AppCompatActivity {

    TextInputEditText task_name_et;
    TextInputEditText task_description_et;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Редактировать задачу");

        task_name_et = findViewById(R.id.task_name_et);
        task_description_et = findViewById(R.id.task_description_et);

        task = new Task(getIntent().getIntExtra("id", -1), getIntent().getStringExtra("task"),
                getIntent().getStringExtra("desc"));

        task_name_et.setText(task.getTask());
        task_description_et.setText(task.getDesc());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.confirm:
                task.setTask(task_name_et.getText().toString());
                task.setDesc(task_description_et.getText().toString());
                if(task.getTask().length() == 0)
                    Toast.makeText(getApplicationContext(), "Пустая задача", Toast.LENGTH_SHORT).show();
                else {
                    upgrade(task.getId() + 1, task.getTask(), task.getDesc(), 0);
                    setResult(Activity.RESULT_OK);
                    this.finish();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void upgrade(int id, String task, String desk, int status) {
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        db.upgradeTask(id, task, desk, status);
        db.closeDB();
    }
}
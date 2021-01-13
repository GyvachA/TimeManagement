package com.management.timemanagement.ui.task_add;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.utils.Task;

import java.sql.Date;
import java.util.Calendar;

public class EditTaskActivity extends AppCompatActivity {

    TextInputEditText task_name_et;
    TextInputEditText task_description_et;
    Task task;
    CalendarView cv;
    long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Редактировать задачу");

        task_name_et = findViewById(R.id.task_name_et);
        task_description_et = findViewById(R.id.task_description_et);

        task = new Task(getIntent().getIntExtra("id", -1), getIntent().getStringExtra("task"),
                getIntent().getStringExtra("desc"), 0, getIntent().getLongExtra("deadline", 0));

        task_name_et.setText(task.getTask());
        task_description_et.setText(task.getDesc());

        cv = findViewById(R.id.task_calendar);
        cv.setDate(task.getDeadline());
        date = cv.getDate();
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                date = calendar.getTimeInMillis();
            }
        });

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
                    upgrade(task.getId(), task.getTask(), task.getDesc(), 0, date);
                    setResult(Activity.RESULT_OK);
                    this.finish();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void upgrade(int id, String task, String desk, int status, long deadline) {
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        db.upgradeTask(id, task, desk, status, deadline);
        db.closeDB();
    }
}
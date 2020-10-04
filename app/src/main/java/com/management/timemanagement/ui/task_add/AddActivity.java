package com.management.timemanagement.ui.task_add;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;

public class AddActivity extends AppCompatActivity {

    TextInputEditText task_name_et;
    TextInputEditText task_description_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setTitle("Добавить задачу");

        task_name_et = findViewById(R.id.task_name_et);
        task_description_et = findViewById(R.id.task_description_et);

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
                String task_name = task_name_et.getText().toString();
                if(task_name.length() == 0)
                    Toast.makeText(getApplicationContext(), "Пустая задача", Toast.LENGTH_SHORT).show();
                else {
                    save(task_name, task_description_et.getText().toString());
                    setResult(Activity.RESULT_OK, this.getIntent());
                    this.finish();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void save(String task, String description) {
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        db.add(task, description, 0);
        db.closeDB();
    }
}
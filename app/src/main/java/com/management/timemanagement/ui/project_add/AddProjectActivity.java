package com.management.timemanagement.ui.project_add;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;

public class AddProjectActivity extends AppCompatActivity {
    TextInputEditText project_title_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_add);

        setTitle("Добавить проект");

        project_title_et = findViewById(R.id.project_name_et);

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
                String project_name = project_title_et.getText().toString();
                if(project_name.length() == 0)
                    Toast.makeText(getApplicationContext(), "Пустое название", Toast.LENGTH_SHORT).show();
                else {
                    save(project_name, "0", 0);
                    setResult(Activity.RESULT_OK, this.getIntent());
                    this.finish();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void save(String name, String color, int status) {
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        db.addProject(name, color, status);
        db.closeDB();
    }

}

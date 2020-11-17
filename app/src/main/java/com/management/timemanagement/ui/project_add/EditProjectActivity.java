package com.management.timemanagement.ui.project_add;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.utils.Project;

public class EditProjectActivity extends AppCompatActivity {
    TextInputEditText project_title_et;
    MaterialButtonToggleGroup chooseColor;
    Project project;

    final int[] color = new int[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_add);
        project = new Project(getIntent().getIntExtra("id", -1),
                getIntent().getIntExtra("color", -1),
                getIntent().getStringExtra("title"),
                getIntent().getIntExtra("status", 0));


        setTitle("Редактировать проект");

        project_title_et = findViewById(R.id.project_name_et);
        chooseColor = findViewById(R.id.color_group_btn);

        project_title_et.setText(project.getTitle());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        chooseColor.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch (checkedId) {
                    case R.id.color_1_btn:
                        project.setCardColor(getResources().getColor(R.color.colorProj1));
                        break;
                    case R.id.color_2_btn:
                        project.setCardColor(getResources().getColor(R.color.colorProj2));
                        break;
                    case R.id.color_3_btn:
                        project.setCardColor(getResources().getColor(R.color.colorProj3));
                        break;
                    case R.id.color_4_btn:
                        project.setCardColor(getResources().getColor(R.color.colorProj4));
                        break;
                    case R.id.color_5_btn:
                        project.setCardColor(getResources().getColor(R.color.colorProj5));
                        break;
                    case R.id.color_6_btn:
                        project.setCardColor(getResources().getColor(R.color.colorProj6));
                        break;
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.confirm:
                project.setTitle(project_title_et.getText().toString());
                if (project.getTitle().length() == 0 || project.getCardColor() == 0)
                    Toast.makeText(getApplicationContext(), "Пустое название", Toast.LENGTH_SHORT).show();
                else {
                    Log.d("TAG", project.toString());
                    upgrade(project.getId(), project.getCardColor(), project.getTitle(),
                            project.getStatus());
                    setResult(Activity.RESULT_OK, this.getIntent());
                    this.finish();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void upgrade(int id, int color, String title, int status) {
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        db.upgradeProject(id, color, title, status);
        db.closeDB();
    }

}

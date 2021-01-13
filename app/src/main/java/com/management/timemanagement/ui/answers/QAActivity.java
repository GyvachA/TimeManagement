package com.management.timemanagement.ui.answers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.management.timemanagement.MainActivity;
import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.data.local.SessionManager;
import com.management.timemanagement.utils.QandA;

import java.util.ArrayList;
import java.util.List;

public class QAActivity extends AppCompatActivity {

    private RecyclerView rv;
    private QAAdapter qaAdapter;
    private List<QandA> qandAS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Вопросы");
        setContentView(R.layout.activity_q_a);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        refresh();
        Log.d("MAINACTIVITY", new SessionManager(this).getLogin());

        rv = findViewById(R.id.qa_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        qaAdapter = new QAAdapter(qandAS);

        rv.setAdapter(qaAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_project, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_project:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Задать вопрос");
                final EditText input = new EditText(this);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inp = input.getText().toString();

                        if (inp.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Заполните поле", Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }

                        DBAdapter adapter = new DBAdapter(getApplicationContext());
                        adapter.openDB();
                        adapter.addQA(new QandA(0, new SessionManager(getApplicationContext()).getLogin(),
                                inp.trim(), null, null, 0));
                        adapter.closeDB();
                        refresh();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refresh() {
        if (!new SessionManager(getApplicationContext()).isModerator()) {
            DBAdapter db = new DBAdapter(this);
            db.openDB();
            qandAS.clear();
            Cursor cursor = db.getQuestionByUserName(new SessionManager(getApplicationContext()).getLogin());
            Log.d("TAG", "ITOG: " + new SessionManager(getApplicationContext()).getLogin());
            while (cursor.moveToNext()) {
                qandAS.add(new QandA(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)
                ));
            }
            db.closeDB();
            return;
        }

        DBAdapter db = new DBAdapter(this);
        db.openDB();
        qandAS.clear();
        Cursor cursor = db.getQuestionByStatusZero();
        while (cursor.moveToNext()) {
            qandAS.add(new QandA(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
            ));
        }
        db.closeDB();
    }
}
package com.management.timemanagement.ui.task_recyclerview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.ui.project_add.EditProjectActivity;
import com.management.timemanagement.ui.task_add.EditTaskActivity;
import com.management.timemanagement.utils.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.management.timemanagement.R.layout.task_rv;

public class TasksAdapter extends RecyclerView.Adapter<TasksHolder> {

    private ArrayList<Task> tasks;
    private Context c;

    public TasksAdapter(ArrayList<Task> arr) {
        tasks = arr;
    }

    @NonNull
    @Override
    public TasksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        c = parent.getContext();

        View view = LayoutInflater.from(c).inflate(task_rv,
                parent, false);

        return new TasksHolder(view);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull final TasksHolder holder, final int position) {
        holder.task_name_tv.setText(tasks.get(position).getTask());
        holder.task_desc_tv.setText(tasks.get(position).getDesc());
        holder.task_status.setChecked(false);
        long date = tasks.get(position).getDeadline();
        if ((date - (72 * 60 * 60 * 1000) <= Calendar.getInstance().getTimeInMillis())
        && (date >= Calendar.getInstance().getTimeInMillis())) {
            holder.deadline_indicator.setColorFilter(Color.YELLOW);
        }
        else if(date < Calendar.getInstance().getTimeInMillis()) {
            holder.deadline_indicator.setColorFilter(Color.RED);
        }
        Log.d("GYASYDGSAYGD", date + " " + Calendar.getInstance().getTimeInMillis());


        holder.task_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int pos = holder.getAdapterPosition();

                    if (pos != -1) {
                        upgrade(pos, tasks.get(pos).getTask(), tasks.get(pos).getDesc(), 1, tasks.get(pos).getDeadline());
                    }
                }
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, EditTaskActivity.class);
                int pos = holder.getAdapterPosition();
                if (pos != -1) {
                    i.putExtra("id", tasks.get(pos).getId());
                    i.putExtra("task", tasks.get(pos).getTask());
                    i.putExtra("desc", tasks.get(pos).getDesc());
                    i.putExtra("deadline", tasks.get(pos).getDeadline());
                    ((Activity) c).startActivityForResult(i, 2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    private void delete(int pos) { //
        DBAdapter db = new DBAdapter(c);
        db.openDB();
        db.deleteTask(tasks.get(pos).getId());
        db.closeDB();
        tasks.remove(pos);
        notifyItemRemoved(pos);
    }

    private void upgrade(int pos, String task, String desk, int status, long deadline) {
        DBAdapter db = new DBAdapter(c);
        db.openDB();
        db.upgradeTask(tasks.get(pos).getId(), task, desk, status, deadline);
        db.closeDB();
        tasks.remove(pos);
        notifyItemRemoved(pos);
    }
}
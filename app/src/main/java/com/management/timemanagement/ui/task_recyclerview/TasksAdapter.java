package com.management.timemanagement.ui.task_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.utils.Task;

import java.util.ArrayList;

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

    @Override
    public void onBindViewHolder(@NonNull final TasksHolder holder, final int position) {
        holder.task_name_tv.setText(tasks.get(position).getTask());
        holder.task_desc_tv.setText(tasks.get(position).getDesc());

        holder.task_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    int pos = holder.getAdapterPosition();

                    if(pos != -1) {
                        holder.task_status.setChecked(false);
                        upgrade(pos, tasks.get(pos).getTask(), tasks.get(pos).getDesc(), 1);
                    }
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
        db.delete(tasks.get(pos).getId());
        db.closeDB();
        tasks.remove(pos);
        notifyItemRemoved(pos);
    }

    private void upgrade(int pos, String task, String desk, int status) {
        DBAdapter db = new DBAdapter(c);
        db.openDB();
        db.upgrade(tasks.get(pos).getId(), task, desk, status);
        db.closeDB();
        tasks.remove(pos);
        notifyItemRemoved(pos);
    }
}
package com.management.timemanagement.ui.task_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.utils.Task;

import java.util.List;

public class TasksReadyAdapter extends RecyclerView.Adapter<TasksReadyAdapter.TasksReadyViewHolder> {
    List<Task> tasks;
    Context c;

    public TasksReadyAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TasksReadyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        c = parent.getContext();
        View view = LayoutInflater.from(c).inflate(R.layout.task_ready_rv,
                parent, false);
        return new TasksReadyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TasksReadyViewHolder holder, int position) {
        holder.task_name.setText(tasks.get(position).getTask());
        holder.task_desc.setText(tasks.get(position).getDesc());

        holder.res_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if(pos != -1){
                    upgrade(pos, tasks.get(pos).getTask(), tasks.get(pos).getDesc(), 0, tasks.get(pos).getDeadline());
                }
            }
        });

        holder.del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if(pos != -1)
                    del(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TasksReadyViewHolder extends RecyclerView.ViewHolder{
        private TextView task_name;
        private TextView task_desc;
        private Button del_btn;
        private Button res_btn;

        public TasksReadyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.task_ready_name_tv);
            task_desc = itemView.findViewById(R.id.task_ready_description_tv);
            del_btn = itemView.findViewById(R.id.task_ready_delete);
            res_btn = itemView.findViewById(R.id.task_ready_restore);
        }
    }

    private void upgrade(int pos, String task, String desk, int status, long deadline) {
        DBAdapter db = new DBAdapter(c);
        db.openDB();
        db.upgradeTask(tasks.get(pos).getId(), task, desk, status, deadline);
        db.closeDB();
        tasks.remove(pos);
        notifyItemRemoved(pos);
    }

    private void del(int pos) {
        DBAdapter db = new DBAdapter(c);
        db.openDB();
        db.deleteTask(pos);
        db.closeDB();
        tasks.remove(pos);
        notifyItemRemoved(pos);
    }
}

package com.management.timemanagement.ui.project_rv;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.utils.Task;

import java.util.List;

public class ProjectTaskAdapter extends RecyclerView.Adapter<ProjectTaskAdapter.ProjectTaskViewHolder> {
    List<Task> tasks;
    Context c;

    public ProjectTaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ProjectTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        c = parent.getContext();
        View view = LayoutInflater.from(c).inflate(R.layout.project_task_rv_item,
                parent, false);
        return new ProjectTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProjectTaskViewHolder holder, int position) {
        holder.project_task_name.setText(tasks.get(position).getTask());
        holder.project_task_desc.setText(tasks.get(position).getDesc());
        holder.status_chk.setChecked(tasks.get(position).getStatus() != 0);

        holder.project_del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if (pos != -1)
                    del(pos);
            }
        });

        holder.status_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int pos = holder.getAdapterPosition();

                if (pos != -1) {
                    upgrade(pos, tasks.get(pos).getTask(), tasks.get(pos).getDesc(),
                            isChecked ? 1 : 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ProjectTaskViewHolder extends RecyclerView.ViewHolder {
        private TextView project_task_name;
        private TextView project_task_desc;
        private Button project_del_btn;
        private CheckBox status_chk;

        public ProjectTaskViewHolder(@NonNull View itemView) {
            super(itemView);
            project_task_name = itemView.findViewById(R.id.project_task_name_tv);
            project_task_desc = itemView.findViewById(R.id.project_task_description_tv);
            project_del_btn = itemView.findViewById(R.id.del_project_task_btn);
            status_chk = itemView.findViewById(R.id.project_task_chk_status);
        }
    }

    private void upgrade(int pos, String task, String desk, int status) {
        DBAdapter db = new DBAdapter(c);
        db.openDB();
        db.upgradeProjectTask(tasks.get(pos).getId(), task, desk, status);//DFGHJK
        db.closeDB();
    }

    private void del(int pos) {
        DBAdapter db = new DBAdapter(c);
        db.openDB();
        db.deleteProjectTask(tasks.get(pos).getId());
        db.closeDB();
        tasks.remove(pos);
        notifyItemRemoved(pos);
    }
}

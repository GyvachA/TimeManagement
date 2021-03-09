package com.management.timemanagement.ui.project_rv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.ui.project.ProjectTaskActivity;
import com.management.timemanagement.ui.project_add.EditProjectActivity;
import com.management.timemanagement.utils.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectsViewHolder> {
    List<Project> projects;
    Context c;

    public ProjectAdapter(List<Project> projects) {
        this.projects = projects;
    }

    public static class ProjectsViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardColor;
        private ImageView cardStatus;
        private TextView titleCard;
        private Button editBtn;
        private Button deleteBtn;
        private CardView projectCard;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardColor = itemView.findViewById(R.id.card_color_iv);
            cardStatus = itemView.findViewById(R.id.status_iv);
            titleCard = itemView.findViewById(R.id.title_project);
            editBtn = itemView.findViewById(R.id.edit_btn_card);
            deleteBtn = itemView.findViewById(R.id.delete_btn_card);
            projectCard = itemView.findViewById(R.id.project_card);
        }
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        c = parent.getContext();
        View view = LayoutInflater.from(c).inflate(R.layout.project_item_rv, parent, false);
        return new ProjectsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProjectsViewHolder holder, int position) {
        holder.titleCard.setText(projects.get(position).getTitle());
        holder.cardColor.setBackgroundColor(projects.get(position).getCardColor());
        if (isReady(projects.get(position).getId()))
            holder.cardStatus.setColorFilter(Color.GREEN);
        else
            holder.cardStatus.setColorFilter(Color.BLACK);

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, EditProjectActivity.class);
                int pos = holder.getAdapterPosition();
                if (pos != -1) {
                    i.putExtra("id", projects.get(pos).getId());
                    i.putExtra("color", projects.get(pos).getCardColor());
                    i.putExtra("title", projects.get(pos).getTitle());
                    i.putExtra("status", projects.get(pos).getStatus());
                    ((Activity) c).startActivityForResult(i, 2);
                }
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if (pos != -1)
                    delete(pos);
            }
        });

        holder.projectCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if(pos != -1) {
                    Intent i = new Intent(c, ProjectTaskActivity.class);
                    i.putExtra("id", projects.get(pos).getId());
                    i.putExtra("name", projects.get(pos).getTitle());
                    ((Activity) c).startActivityForResult(i, 2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }


    private void delete(int pos) {
        DBAdapter db = new DBAdapter(c);
        db.openDB();
        db.deleteProject(projects.get(pos).getId());
        db.closeDB();
        projects.remove(pos);
        notifyItemRemoved(pos);
    }

    private boolean isReady(int id) {
        DBAdapter db = new DBAdapter(c);
        db.openDB();
        List<Integer> statuses = new ArrayList<>();
        Cursor cursor = db.getProjectTaskDetails(id);
        while (cursor.moveToNext())
            statuses.add(cursor.getInt(3));
        if (!cursor.moveToFirst())
            return false;
        db.closeDB();
        for (int i : statuses) {
            if (i == 0)
                return false;
        }
        return true;
    }

}

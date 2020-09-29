package com.management.timemanagement.ui.project_rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.R;
import com.management.timemanagement.utils.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectsViewHolder> {
    List<Project> projects = new ArrayList<>();

    public ProjectAdapter(List<Project> projects) {
        this.projects = projects;
    }

    public static class ProjectsViewHolder extends RecyclerView.ViewHolder{
        public ImageView cardColor;
        public ImageView cardStatus;
        public TextView titleCard;
        public ImageButton editBtn;
        public ImageButton deleteBtn;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardColor = itemView.findViewById(R.id.card_color_iv);
            cardStatus = itemView.findViewById(R.id.status_iv);
            titleCard = itemView.findViewById(R.id.title_project);
            editBtn = itemView.findViewById(R.id.edit_btn_card);
            deleteBtn = itemView.findViewById(R.id.delete_btn_card);
        }
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item_rv, parent, false);
        return new ProjectsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {
        holder.titleCard.setText(projects.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }


}

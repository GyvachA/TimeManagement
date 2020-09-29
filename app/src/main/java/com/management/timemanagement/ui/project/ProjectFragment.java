package com.management.timemanagement.ui.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.ui.project_add.AddProjectActivity;
import com.management.timemanagement.ui.project_rv.ProjectAdapter;
import com.management.timemanagement.ui.task_add.AddActivity;
import com.management.timemanagement.utils.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectFragment extends Fragment {

    List<Project> projects = new ArrayList<>();
    RecyclerView projects_rv;
    ProjectAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProjectViewModel settingsViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);
        View root = inflater.inflate(R.layout.fragment_project, container, false);
        setHasOptionsMenu(true);
        refresh();

        projects_rv = root.findViewById(R.id.projects_rv);
        adapter = new ProjectAdapter(projects);

        projects_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        projects_rv.setAdapter(adapter);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar_project, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_project:
                Intent intent = new Intent(getActivity(), AddProjectActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        public void refresh() {
        DBAdapter db = new DBAdapter(getContext());
        db.openDB();

        projects.clear();

        Cursor cursor = db.getProjectDetails();


        while(cursor.moveToNext()) {
            projects.add(new Project(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)));
        }

        db.closeDB();
    }

}

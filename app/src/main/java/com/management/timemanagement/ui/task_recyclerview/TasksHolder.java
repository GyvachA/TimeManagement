package com.management.timemanagement.ui.task_recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.R;

class TasksHolder extends RecyclerView.ViewHolder {

    TextView task_name_tv;
    TextView task_desc_tv;
    CheckBox task_status;
    Button edit;
    ImageView deadline_indicator;

    TasksHolder(@NonNull View itemView) {
        super(itemView);
        task_name_tv = itemView.findViewById(R.id.task_name_tv);
        task_desc_tv = itemView.findViewById(R.id.task_description_tv);
        task_status = itemView.findViewById(R.id.chk_status);
        edit = itemView.findViewById(R.id.edit_btn_task);
        deadline_indicator = itemView.findViewById(R.id.deadline_ind);
    }

}
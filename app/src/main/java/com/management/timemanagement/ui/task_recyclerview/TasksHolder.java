package com.management.timemanagement.ui.task_recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.R;
import com.management.timemanagement.ui.task_add.AddActivity;

class TasksHolder extends RecyclerView.ViewHolder {

    TextView task_name_tv;
    TextView task_desc_tv;
    CheckBox task_status;

    TasksHolder(@NonNull View itemView) {
        super(itemView);

//        task_txt = itemView.findViewById(R.id.task_txt);
//        delete_btn = itemView.findViewById(R.id.task_del);
//        edit_btn = itemView.findViewById(R.id.task_edit);

        task_name_tv = itemView.findViewById(R.id.task_name_tv);
        task_desc_tv = itemView.findViewById(R.id.task_description_tv);
        task_status = itemView.findViewById(R.id.chk_status);

    }

}
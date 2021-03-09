package com.management.timemanagement.ui.answers;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.data.local.SessionManager;
import com.management.timemanagement.utils.QandA;

import java.util.List;

public class QAAdapter extends RecyclerView.Adapter<QAAdapter.QAViewHolder> {

    private List<QandA> qandAS;
    Context c;

    public QAAdapter(List<QandA> qandAS) {
        this.qandAS = qandAS;
    }

    public static class QAViewHolder extends RecyclerView.ViewHolder {

        private TextView userName;
        private TextView userQuestion;
        private TextView moderName;
        private TextView moderAnswer;
        private TextView answer_btn;

        public QAViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            userQuestion = itemView.findViewById(R.id.user_question);
            moderName = itemView.findViewById(R.id.moder_name);
            moderAnswer = itemView.findViewById(R.id.moder_answer);
            answer_btn = itemView.findViewById(R.id.answer_button);
        }

        public void bind(QandA q) {
            userName.setText(q.getUserName());
            userQuestion.setText(q.getUserAnswer());

            if (q.getStatus() > 0) {
                moderName.setText(q.getModerName());
                moderAnswer.setText(q.getModerAnswer());
                answer_btn.setVisibility(View.GONE);
                return;
            }

            if (!new SessionManager(answer_btn.getContext()).isModerator()) {
                answer_btn.setVisibility(View.GONE);
            }

            moderName.setVisibility(View.GONE);
            moderAnswer.setVisibility(View.GONE);
        }
    }


    @NonNull
    @Override
    public QAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        c = parent.getContext();
        View view = LayoutInflater.from(c).inflate(R.layout.qa_item, parent, false);
        return new QAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QAViewHolder holder, final int position) {
        holder.bind(qandAS.get(position));
        holder.answer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Ответ");
                final EditText input = new EditText(c);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inp = input.getText().toString();
                        int pos = holder.getAdapterPosition();
                        if (inp.isEmpty()) {
                            Toast.makeText(c, "Заполните поле", Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }

                        DBAdapter adapter = new DBAdapter(c);
                        adapter.openDB();
                        adapter.upgradeQuestion(qandAS.get(pos).getId(), new SessionManager(c).getLogin(), inp.trim());
                        adapter.closeDB();

                        qandAS.remove(pos);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return qandAS.size();
    }
}

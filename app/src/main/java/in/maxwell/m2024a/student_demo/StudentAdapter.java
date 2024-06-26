package in.maxwell.m2024a.student_demo;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.maxwell.m2024a.R;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private final ArrayList<Student> alStudents;

    public interface OnStudentClickListener {
        void onStudentClick(int position, Student student);
    }
    private OnStudentClickListener onStudentClickListener;

    public void setOnStudentClickListener(OnStudentClickListener onStudentClickListener) {
        this.onStudentClickListener = onStudentClickListener;
    }

    public StudentAdapter(ArrayList<Student> alStudents) {
        this.alStudents = alStudents;
    }

    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_student, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {

        holder.tvStudentID.setText(alStudents.get(position).getStudentID());
        holder.tvStudentName.setText(String.format("%s %s", alStudents.get(position).getFirstName(), alStudents.get(position).getLastName()));

        if( alStudents.get(position).getGender() == 1 ){
            holder.tvStudentName.setTextColor(Color.BLUE);
        }
        else{
            if( alStudents.get(position).getGender() == 0 ){
                holder.tvStudentName.setTextColor(Color.RED);
            }
        }

        // set/attach the click listener
        holder.itemView.setOnClickListener(view -> {
            onStudentClickListener.onStudentClick(position, alStudents.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return alStudents.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView tvStudentID;
        TextView tvStudentName;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudentID = itemView.findViewById(R.id.tvStudentID);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
        }
    }

}

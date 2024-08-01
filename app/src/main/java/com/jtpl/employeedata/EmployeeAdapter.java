package com.jtpl.employeedata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList;

    public EmployeeAdapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.textViewName.setText(employee.getName());
        holder.textViewAddress.setText(employee.getAddress());
        holder.textViewDepartment.setText(employee.getDepartment());
        holder.textViewGender.setText(employee.getGender());
        holder.textViewFresher.setText(employee.isFresher() ? "Yes" : "No");
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName, textViewAddress, textViewDepartment, textViewGender, textViewFresher;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            textViewDepartment = itemView.findViewById(R.id.textViewDepartment);
            textViewGender = itemView.findViewById(R.id.textViewGender);
            textViewFresher = itemView.findViewById(R.id.textViewFresher);
        }
    }
}
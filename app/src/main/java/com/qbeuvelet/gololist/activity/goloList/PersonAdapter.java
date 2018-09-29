package com.qbeuvelet.gololist.activity.goloList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qbeuvelet.gololist.App;
import com.qbeuvelet.gololist.R;
import com.qbeuvelet.gololist.model.Customer;
import com.qbeuvelet.gololist.model.Employee;
import com.qbeuvelet.gololist.model.Person;

import java.util.List;
import java.util.Vector;

public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	
	private static final int TYPE_CUSTOMER = 0;
	private static final int TYPE_EMPLOYEE = 1;
	
	private LayoutInflater layoutInflater;
	private List<Person>   persons;
	
	public PersonAdapter(@NonNull Context context)
	{
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.persons = new Vector<>();
	}
	
	public PersonAdapter(@NonNull Context context, List<Person> persons)
	{
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.persons = persons;
	}
	
	public void setList(List<Person> persons)
	{
		this.persons = persons;
	}
	
	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		switch (viewType)
		{
			case TYPE_EMPLOYEE:
				return new EmployeeViewHolder(layoutInflater.inflate(R.layout.item_list_employee, parent, false));
			default:
				return new CustomerViewHolder(layoutInflater.inflate(R.layout.item_list_customer, parent, false));
		}
	}
	
	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
	{
		switch (holder.getItemViewType())
		{
			case TYPE_CUSTOMER:
				Customer customer = (Customer) persons.get(position);
				CustomerViewHolder customerViewHolder = (CustomerViewHolder) holder;
				customerViewHolder.textViewName.setText(customer.getName());
				customerViewHolder.textViewPhone.setText(customer.getPhoneNumber());
				break;
			case TYPE_EMPLOYEE:
				Employee employee = (Employee) persons.get(position);
				EmployeeViewHolder employeeViewHolder = (EmployeeViewHolder) holder;
				employeeViewHolder.textViewName.setText(App.getContext().getString(R.string.employee, employee.getName()));
				employeeViewHolder.textViewPhone.setText(employee.getPhoneNumber());
				employeeViewHolder.textViewEmail.setText(employee.getEmail());
				break;
		}
	}
	
	@Override
	public int getItemViewType(int position)
	{
		Person person = persons.get(position);
		if (person instanceof Customer)
		{
			return TYPE_CUSTOMER;
		}
		else
		{
			return TYPE_EMPLOYEE;
		}
	}
	
	@Override
	public int getItemCount()
	{
		return persons.size();
	}
	
	public static class CustomerViewHolder extends RecyclerView.ViewHolder {
		TextView textViewName;
		TextView textViewPhone;
		
		public CustomerViewHolder(View itemView)
		{
			super(itemView);
			textViewName = itemView.findViewById(R.id.person_name);
			textViewPhone = itemView.findViewById(R.id.person_phoneNumber);
		}
	}
	
	public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
		TextView textViewName;
		TextView textViewPhone;
		TextView textViewEmail;
		
		public EmployeeViewHolder(View itemView)
		{
			super(itemView);
			textViewName = itemView.findViewById(R.id.person_name);
			textViewPhone = itemView.findViewById(R.id.person_phoneNumber);
			textViewEmail = itemView.findViewById(R.id.person_email);
		}
	}
}

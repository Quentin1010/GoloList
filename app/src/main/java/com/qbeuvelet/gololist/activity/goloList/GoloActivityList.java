package com.qbeuvelet.gololist.activity.goloList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qbeuvelet.gololist.R;
import com.qbeuvelet.gololist.dataprovider.ApiCaller;
import com.qbeuvelet.gololist.model.Customer;
import com.qbeuvelet.gololist.model.Employee;
import com.qbeuvelet.gololist.model.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class GoloActivityList extends AppCompatActivity {
	
	private RecyclerView  listPersons;
	private PersonAdapter personAdapter;
	private ApiCallerTask task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_golo_list);
		
		initView();
		
		personAdapter = new PersonAdapter(this);
		listPersons.setAdapter(personAdapter);
		
		task = new ApiCallerTask(personList -> {
			personAdapter.setList(personList);
			personAdapter.notifyDataSetChanged();
		});
		task.execute();
	}
	
	private void initView()
	{
		listPersons = findViewById(R.id.listPersons);
		listPersons.setLayoutManager(new LinearLayoutManager(this));
	}
	
	@Override
	protected void onDestroy()
	{
		if (task != null)
		{
			task.cancel(true);
		}
		super.onDestroy();
	}
	
	private static class ApiCallerTask extends AsyncTask<Void, Void, List<Person>> {
		
		private AsyncResponse taskOnPostExecute;
		
		public ApiCallerTask(AsyncResponse taskOnPostExecute)
		{
			this.taskOnPostExecute = taskOnPostExecute;
		}
		
		@Override
		protected List<Person> doInBackground(Void... voids)
		{
			Vector<Person> personsVector = new Vector<>();
			
			Customer[] customers = ApiCaller.getCustomers();
			if (customers != null)
			{
				personsVector.addAll(Arrays.asList(customers));
			}
			
			Employee[] employees = ApiCaller.getEmployees();
			if (employees != null)
			{
				personsVector.addAll(Arrays.asList(employees));
			}
			
			Collections.sort(personsVector, (person1, person2) -> person1.getName().compareTo(person2.getName()));
			
			return personsVector;
		}
		
		@Override
		protected void onPostExecute(List<Person> people)
		{
			taskOnPostExecute.processFinish(people);
		}
	}
	
}

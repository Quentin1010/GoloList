package com.qbeuvelet.gololist.activity.goloList;

import com.qbeuvelet.gololist.model.Person;

import java.util.List;

public interface AsyncResponse {
	
	void processFinish(List<Person> personList);
}

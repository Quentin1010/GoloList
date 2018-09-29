package com.qbeuvelet.gololist;

import android.app.Application;
import android.content.Context;

public class App extends Application {
	
	private static Context applicationContext;
	
	@Override
	public void onCreate()
	{
		applicationContext = getApplicationContext();
		super.onCreate();
	}
	
	public static Context getContext()
	{
		return applicationContext;
	}
}

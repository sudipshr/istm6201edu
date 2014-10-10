package com.istmszone.andoird.mock;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.istmszone.andoird.R;

public class AuthenticationService {
	
	public static boolean login(Activity activity, String userEmail, String password){
		
		Context context = activity.getApplicationContext();
		SharedPreferences sharedPref = context.getSharedPreferences(
		        activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		
		String storePass = sharedPref.getString(userEmail, null);
		
		return (storePass != null && storePass.equals(password));
		
	
	}
	
	
	public static void register(Activity activity, String userEmail, String password){
		
		Context context = activity.getApplicationContext();
		SharedPreferences sharedPref = context.getSharedPreferences(
		        activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit().putString(userEmail, password);
		editor.commit();
	}
		
		
		
		
	
	

}

package com.istmszone.andoird;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

public class NotificationActivity extends FragmentActivity {

	ListView notificationList = null;
	List<String> nList = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	Button addFriendButton = null;
	
	EditText tv = null;
	AlertDialog.Builder alertBuilder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);

		addFriendButton = (Button) findViewById(R.id.addFriend);
		notificationList = (ListView) findViewById(R.id.invitationList);
		notificationList.setBackgroundColor(Color.CYAN);
		
		tv = (EditText)findViewById (R.id.friend);
		tv.setTextColor(Color.BLACK);
		adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, nList);
		notificationList.setAdapter(adapter);
		
		alertBuilder = new AlertDialog.Builder(this);
		
		notificationList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				final int index = position;
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which){
				        case DialogInterface.BUTTON_POSITIVE:
				        	nList.remove(index);
				        	adapter.notifyDataSetChanged();
				            break;

				        case DialogInterface.BUTTON_NEGATIVE:
				            //No button clicked
				            break;
				        }
						
					}
					
					
				};
				
				
				alertBuilder.setMessage("Remove from invite list?").setPositiveButton("Yes", dialogClickListener)
				    .setNegativeButton("No", dialogClickListener).show();
				
				
				return false;
			}
		

		});
		
		addFriendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				nList.add(tv.getText().toString());
				adapter.notifyDataSetChanged();
				tv.getText().clear();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void showTimePicker(View view) {

		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(this.getFragmentManager(), "timePicker");
	}
	
	public void showDatePicker(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(this.getFragmentManager(), "datePicker");
	}
	
	public void listEditDialog(View view) {


	}

	public static class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR);
			int minute = c.get(Calendar.MINUTE);

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			
			TextView time = (TextView)this.getActivity().findViewById(R.id.time);
			time.setText(hourOfDay+":"+minute);
		}
	}

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			//return new DatePickerDialog(getActivity().getApplicationContext(), this, year, month, day);
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			TextView date = (TextView)this.getActivity().findViewById(R.id.date);
			
			date.setText(month+"/"+day+"/"+year);
		}
	}
}

package sph.durga.sexercisetracker;

import sph.durga.sexercisetracker.db.ExeciseByMonth;
import sph.durga.sexercisetracker.db.SqlHelper;
import sph.durga.sexercisetracker.db.TotalPoints;
import sph.durga.sexercisetracker.db.TotalPointsmmyy;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class WorkOutFragActivity extends FragmentActivity implements OnItemSelectedListener, OnClickListener 
{
	Spinner workoutSpinner;
	EditText countText;
	TextView typeText;
	TextView pointsText;
	Button updateBtn;
	Date date;
	int points;
	SqlHelper dbHelper;
	String ptsEarnedTxt = "Total points earned for the day: ";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout);
		workoutSpinner = (Spinner) findViewById(R.id.workoutSpinner);
		countText = (EditText) findViewById(R.id.countText);
		typeText = (TextView) findViewById(R.id.typeText);
		pointsText = (TextView) findViewById(R.id.pointsText);
		updateBtn = (Button) findViewById(R.id.updateBtn);
		updateBtn.setOnClickListener(this);
		workoutSpinner.setOnItemSelectedListener(this);
		Intent in = this.getIntent();
		Bundle extras = in.getExtras();
		date = new Date(extras.getInt("day"), extras.getInt("month"), extras.getInt("year"));
		//get points from DB for the day so far and populate in the pointstext
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		dbHelper = new SqlHelper(this);
		pointsText.setText(ptsEarnedTxt + GetPointsDB());
	}

	@Override
	protected void onPause()
	{
		dbHelper.close();
		super.onPause();
	}

	private String GetPointsDB() 
	{
		//get points from the database and display it for the day in the week
		ExeciseByMonth exerciseday = new ExeciseByMonth();
		int dayPoints = exerciseday.GetDayPoints(dbHelper, date.get_month(), date.get_day());
		return String.valueOf(dayPoints);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View view, int position,
			long arg3) 
	{
		if(position != 1) //cardio
		{
			countText.setText("20");
			typeText.setText("minutes");
		}
		else if(position == 1)
		{
			countText.setText("10");
			typeText.setText("rounds");
		}	    
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) 
	{
		countText.setText("");
		typeText.setText("");
	}

	@Override
	public void onClick(View view) 
	{
		if(view == updateBtn)
		{
			int mon = date.get_month();
			int dt = date.get_day();
			String type = workoutSpinner.getSelectedItem().toString();
			TotalPointsmmyy totalpointsmmyy = new TotalPointsmmyy();
			totalpointsmmyy.setMon(mon);
			
			// update points in DB
			int points = Getpoints(workoutSpinner.getSelectedItem().toString(), Integer.parseInt(countText.getText().toString()));
			ExeciseByMonth exercisemonth = new ExeciseByMonth();
			boolean  isinsertorupdate = exercisemonth.GetDayPoints(dbHelper, mon, dt, type);
			int beforedayupdatepoints = exercisemonth.getTotaldaypoints();
			exercisemonth.InsertORUpdateDayPoints(dbHelper, date.get_month(), dt, type, points, isinsertorupdate);		
			//moth set in the onCreate method
			int monthpoints = totalpointsmmyy.GetTotalMonthPoints(dbHelper, mon);
			int afterdayupdatepoints = exercisemonth.getTotaldaypoints();
			totalpointsmmyy.UpdateMonthTotalPoints(dbHelper, monthpoints + afterdayupdatepoints - beforedayupdatepoints);
			TotalPoints totalPoints = new TotalPoints();
			int dbPoints = totalPoints.GetTotalPoints(dbHelper);
			totalPoints.UpdateTotalPoints(dbHelper, dbPoints + afterdayupdatepoints - beforedayupdatepoints, dbPoints);
			pointsText.setText(ptsEarnedTxt + afterdayupdatepoints);
			Toast.makeText(this, "Monthly points:" + String.valueOf(totalpointsmmyy.GetTotalMonthPoints(dbHelper)  +  "Total points:" + totalPoints.getPoints()), Toast.LENGTH_SHORT).show();
			//Toast.makeText(this, String.valueOf(totalPoints.GetTotalPoints(dbHelper)), Toast.LENGTH_SHORT).show();
		}
	}

	private int Getpoints(String type, int time)
	{
		int points = 0;
		if(type.equals(Constants.workoutArray[1]))
		{
			points = time;
		}
		else 
		{
			points = (int) (time * 0.5);
		}
		return points;
	}
}

package sph.durga.sexercisetracker.charts;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.achartengine.GraphicalView;

import sph.durga.sexercisetracker.Constants;
import sph.durga.sexercisetracker.db.ExeciseByMonth;
import sph.durga.sexercisetracker.db.SqlHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup.LayoutParams;

public class DisplayMonthChartActivity extends FragmentActivity
{
	int selectedMonth;
	SqlHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle arg0)
	{	
		super.onCreate(arg0);
		if(getIntent().getExtras() != null)
		{
			selectedMonth = getIntent().getExtras().getInt("month");
		}
	}
	
	@Override
	protected void onResume() 
	{
		dbHelper = new SqlHelper(this);
		ExeciseByMonth exmon = new ExeciseByMonth();
		Hashtable<Integer, Integer> getDayPointsTable = exmon.GetMonthPointsByDay(dbHelper, selectedMonth);
		int[] labels = new int[Constants.daysOfMonth[selectedMonth - 1]];
		for(int i =0; i < Constants.daysOfMonth[selectedMonth - 1]; i++)
		{
			labels[i] = i + 1;
		}
		GraphicalView view = new LineGraph().execute(this, Constants.mon[selectedMonth - 1], labels, GetDays(getDayPointsTable.keySet()), GetPoints(getDayPointsTable.values()));
		addContentView(view, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		super.onResume();
	}
	
	@Override
	protected void onPause()
	{
		dbHelper.close();
		super.onPause();
	}
	
	private int[] GetDays(Set<Integer> keys)
	{
		int[] days = new int[keys.size()];
		Iterator<Integer> it = keys.iterator();
		int i =0;
		while(it.hasNext())
		{
			days[i ++] = it.next();
		}
		return days;
	}

	private int[] GetPoints(Collection<Integer> points)
	{
		int[] ddpoints = new int[points.size()];
		Iterator<Integer> it = points.iterator();
		int i =0;
		while(it.hasNext())
		{
			ddpoints[i] = it.next().intValue();
			i++;
		}
		return ddpoints;
	}
}

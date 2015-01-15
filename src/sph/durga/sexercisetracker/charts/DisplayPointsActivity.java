package sph.durga.sexercisetracker.charts;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.achartengine.GraphicalView;

import sph.durga.sexercisetracker.Constants;
import sph.durga.sexercisetracker.R;
import sph.durga.sexercisetracker.db.SqlHelper;
import sph.durga.sexercisetracker.db.TotalPoints;
import sph.durga.sexercisetracker.db.TotalPointsmmyy;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class DisplayPointsActivity extends FragmentActivity 
{
	TextView totalPointsTxt;
	//SingleDonutGraph donutGraph;
	PieChart piechart;
	SqlHelper dbHelper;
	int totalPoints;
	Hashtable<String, Double> yearmmpoints;
	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
	//	donutGraph = new SingleDonutGraph();
		piechart = new PieChart();
		setContentView(R.layout.points_display);
		totalPointsTxt = (TextView)findViewById(R.id.totalPointsTxt);
		//get min month from DB		
		//get start month from DB
		//	donutGraph.execute(this, Constants.COLORSARR, labels, values);
	}

	private int GetYearPointsDB() 
	{
		TotalPoints totalPoints = new TotalPoints();	
		return totalPoints.GetTotalPoints(dbHelper);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		dbHelper = new SqlHelper(this);
		totalPoints = GetYearPointsDB(); //populate it in text view
		//get points for each month from DB
		totalPointsTxt.setText(totalPointsTxt.getText() + " " + String.valueOf(totalPoints));
		TotalPointsmmyy totalPtsmmyy = new TotalPointsmmyy();
		yearmmpoints = totalPtsmmyy.GetYearPointsByMonth(dbHelper);
		if(yearmmpoints.size() > 0)
		{
			GraphicalView view = piechart.execute(this.getApplicationContext(), Constants.COLORSARR, GetMonths(yearmmpoints.keySet()), GetMMPoints(yearmmpoints.values()));
			this.addContentView(view, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}

	private String[] GetMonths(Set<String> keys)
	{
		String[] months = new String[keys.size()];
		Iterator<String> it = keys.iterator();
		int i =0;
		while(it.hasNext())
		{
			months[i ++] = it.next();
		}
		return months;
	}

	private double[] GetMMPoints(Collection<Double> points)
	{
		double[] mmpoints = new double[points.size()];
		Iterator<Double> it = points.iterator();
		int i =0;
		while(it.hasNext())
		{
			mmpoints[i ++] = it.next();
		}
		return mmpoints;
	}

	@Override
	protected void onPause()
	{
		dbHelper.close();
		super.onPause();
	}
}

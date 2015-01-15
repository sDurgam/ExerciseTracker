package sph.durga.sexercisetracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import sph.durga.sexercisetracker.charts.DisplayMonthChartActivity;
import sph.durga.sexercisetracker.charts.DisplayPointsActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	static Calendar cal;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	Calendar calendar;
	static Context ctx;
	static int secnum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		ctx = getApplicationContext();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void DisplayPoints(View v)
	{
		Intent in = new Intent(this, DisplayPointsActivity.class);
		startActivity(in);
	}
	
	public void DisplayMonthChart(View v)
	{
		Intent in = new Intent(this, DisplayMonthChartActivity.class);
		in.putExtra("month", secnum - 1);
		startActivity(in);
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

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show july, august, september, october, november, december total pages.
			cal = Calendar.getInstance();
			return (12 - cal.get(Calendar.MONTH));
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber + GetcurrentMonth());
			fragment.setArguments(args);
			return fragment;
		}

		private static int GetcurrentMonth()
		{
			if(cal == null)
			{
				cal = Calendar.getInstance();
			}
			return (cal.get(Calendar.MONTH));
		}

		public PlaceholderFragment() {
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));

			secnum = getArguments().getInt(ARG_SECTION_NUMBER);
			GridView gridView = (GridView) rootView.findViewById(R.id.section_grid);
			GridCellAdapter adapter = new GridCellAdapter(secnum, Constants.currentYear, textView);
			gridView.setAdapter(adapter);
			return rootView;
		}

		public class GridCellAdapter extends BaseAdapter implements OnClickListener
		{
			private static final String tag = "GridCellAdapter";
			//private final Context _context;
			private final List<Integer> list;
			private static final int DAY_OFFSET = 1;
			private final String[] weekdays = new String[] { "Sun", "Mon", "Tue",
					"Wed", "Thu", "Fri", "Sat" };
			private final String[] months = { "January", "February", "March",
					"April", "May", "June", "July", "August", "September",
					"October", "November", "December" };
			
			private int currentDayOfMonth;
			private int currentWeekDay;
			private Button gridcell;
			private int selectedMonth;
			private final SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"dd-MMM-yyyy");

			// Days in Current Month
			public GridCellAdapter(int month, int year, TextView txtView)
			{
				super();
				this.list = new ArrayList<Integer>();
				if(Constants.daysOfMonth.length >= (month - 1))
				{
					for(int i =0; i < Constants.daysOfMonth[month - 1]; i++)
					{
						list.add(i + 1);
					}
				}
				selectedMonth = month - 1;
				txtView.setText(months[selectedMonth]);
			}

			private String getMonthAsString(int i) {
				return months[i];
			}

			private String getWeekDayAsString(int i) {
				return weekdays[i];
			}

			private int getNumberOfDaysOfMonth(int i) {
				return Constants.daysOfMonth[i];
			}

			@Override
			public int getCount() {
				return list.size();
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent)
			{
				View row = convertView;
				if (row == null)
				{
					LayoutInflater inflater = (LayoutInflater) ctx
							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					row = inflater.inflate(R.layout.screen_gridcell, parent, false);
				}
				Integer day_color = list.get(position);
				int day = day_color - 1;
				// Set the Day GridCell
				gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
				String dayStr = String.valueOf(day_color);
				gridcell.setOnClickListener(this);
				gridcell.setText(dayStr);
				gridcell.setTag(day_color);
				return row;
			}

			public int getCurrentDayOfMonth() {
				return currentDayOfMonth; 
			}

			private void setCurrentDayOfMonth(int currentDayOfMonth) {
				this.currentDayOfMonth = currentDayOfMonth;
			}

			public void setCurrentWeekDay(int currentWeekDay) {
				this.currentWeekDay = currentWeekDay;
			}

			public int getCurrentWeekDay() {
				return currentWeekDay;
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public void onClick(View v)
			{
				int day =  (Integer) v.getTag();
				Intent in = new Intent(ctx, WorkOutFragActivity.class);
				in.putExtra("day", day);
				in.putExtra("month", selectedMonth + 1);
				in.putExtra("year", Constants.currentYear);
				startActivity(in);

			}
		}
	}

}

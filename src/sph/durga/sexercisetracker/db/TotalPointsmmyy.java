package sph.durga.sexercisetracker.db;

import java.util.Hashtable;

import sph.durga.sexercisetracker.Constants;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TotalPointsmmyy
{
	int year;
	int mon;
	int points;
	int totalpoints;

	public int getYear() 
	{
		return year;
	}
	public void setYear(int year) 
	{
		this.year = year;
	}
	public int getMon() 
	{
		return mon;
	}
	public void setMon(int mon) 
	{
		this.mon = mon;
	}
	public int getPoints() 
	{
		return points;
	}
	public void setPoints(int points) 
	{
		this.points = points;
	} 

	public int GetTotalYearPoints(SqlHelper helper)
	{
		int totalpoints = 0;
		SQLiteDatabase reader = helper.getReadableDatabase();
		Cursor cursor = reader.query(SqlHelper.TABLE_TOTAL_POINTS_YY_MM, new String[]{SqlHelper.KEY_TOTAL_POINTS_YY_MM}, null, null, null, null, null, null);
		cursor.moveToFirst();
		if(cursor.getCount() != 0)
		{
			do
			{
				totalpoints += cursor.getInt(0);
			}while(cursor.moveToNext());
		}
		cursor.close();
		reader.close();
		return totalpoints;
	}

	public int GetTotalMonthPoints(SqlHelper helper, int mon)
	{
		int totalpoints = 0;
		SQLiteDatabase reader = helper.getReadableDatabase();
		String whereClause = SqlHelper.KEY_TOTAL_POINTS_MONTH + "= ?";
		String[] whereArgs = { Integer.toString(mon)}; 
		Cursor cursor = reader.query(SqlHelper.TABLE_TOTAL_POINTS_YY_MM, new String[]{SqlHelper.KEY_TOTAL_POINTS_YY_MM}, whereClause, whereArgs, null, null, null, null);
		cursor.moveToFirst();
		if(cursor.getCount() != 0)
		{
			do
			{
				totalpoints += cursor.getInt(0);
			}while(cursor.moveToNext());
		}
		cursor.close();
		reader.close();
		return totalpoints;
	}
	
	
	
	
	public Hashtable<String, Double> GetYearPointsByMonth(SqlHelper dbHelper)
	{
		Hashtable<String, Double> monthlypointstable = new Hashtable<String, Double>();
		SQLiteDatabase writer = dbHelper.getWritableDatabase();
		Cursor cursor = writer.query(SqlHelper.TABLE_TOTAL_POINTS_YY_MM, new String[] { SqlHelper.KEY_TOTAL_POINTS_MONTH, SqlHelper.KEY_TOTAL_POINTS_YY_MM }, null, null, null, null, null);
		cursor.moveToFirst();
		if(cursor.getCount() != 0)
		{
			do
			{
				monthlypointstable.put(Constants.mon[cursor.getInt(0) - 1], (double) cursor.getInt(1));
			}while(cursor.moveToNext());
		}
		return monthlypointstable;
	}


	public int GetTotalMonthPoints(SqlHelper helper)
	{
		int totalpoints = 0;
		SQLiteDatabase reader = helper.getReadableDatabase();
		String whereClause = SqlHelper.KEY_TOTAL_POINTS_MONTH + "= ?";
		String[] whereArgs = { Integer.toString(this.mon)}; 
		Cursor cursor = reader.query(SqlHelper.TABLE_TOTAL_POINTS_YY_MM, new String[]{SqlHelper.KEY_TOTAL_POINTS_YY_MM}, whereClause, whereArgs, null, null, null, null);
		cursor.moveToFirst();
		if(cursor.getCount() != 0)
		{
			do
			{
				totalpoints += cursor.getInt(0);
			}while(cursor.moveToNext());
		}
		cursor.close();
		reader.close();
		return totalpoints;
	}

	public void UpdateMonthTotalPoints(SqlHelper helper, int mon, int points)
	{
		//		int pts =  GetTotalMonthPoints(helper, mon);
		//		points += pts;
		this.points = points;
		SQLiteDatabase writer = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(SqlHelper.KEY_TOTAL_POINTS_YEAR, Constants.currentYear);
		cv.put(SqlHelper.KEY_TOTAL_POINTS_MONTH, mon);
		cv.put(SqlHelper.KEY_TOTAL_POINTS_YY_MM, points);
		writer.insertWithOnConflict(SqlHelper.TABLE_TOTAL_POINTS_YY_MM, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
		writer.close();
	}

	public void UpdateMonthTotalPoints(SqlHelper helper, int points)
	{
		//		int pts =  GetTotalMonthPoints(helper, this.mon);
		//		points += pts;
		this.points = points;
		SQLiteDatabase writer = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(SqlHelper.KEY_TOTAL_POINTS_YEAR, 2014);
		cv.put(SqlHelper.KEY_TOTAL_POINTS_MONTH, this.mon);
		cv.put(SqlHelper.KEY_TOTAL_POINTS_YY_MM, points);
		writer.insertWithOnConflict(SqlHelper.TABLE_TOTAL_POINTS_YY_MM, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
		writer.close();
	}
}

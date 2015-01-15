package sph.durga.sexercisetracker.db;

import java.util.Hashtable;

import sph.durga.sexercisetracker.Constants;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ExeciseByMonth 
{
	int mon;
	int day;
	String[] type;
	Integer[] points;
	int totaldaypoints;

	public int getTotaldaypoints() {
		return totaldaypoints;
	}
	public void setTotaldaypoints(int totaldaypoints) {
		this.totaldaypoints = totaldaypoints;
	}
	public int getMon() {
		return mon;
	}
	public void setMon(int mon) {
		this.mon = mon;
	}
	public int getDay() {
		return day;
	}
	
	public Hashtable<Integer, Integer> GetMonthPointsByDay(SqlHelper dbHelper, int mon)
	{
		String[] type;
		Hashtable<Integer, Integer> dailypointstable = new Hashtable<Integer, Integer>();
		SQLiteDatabase writer = dbHelper.getWritableDatabase();
		Cursor cursor = writer.query(Constants.mon[mon - 1], new String[] { SqlHelper.KEY_DAY, SqlHelper.KEY_TYPE, SqlHelper.KEY_POINTS }, null, null, null, null, null);
		cursor.moveToFirst();
		int count = cursor.getCount();
		type = new String[count];
		int i =0;
		int day;
		if(count != 0)
		{
			do
			{
				day = cursor.getInt(0);
				if(dailypointstable.containsKey(day))
				{
					dailypointstable.put(day, dailypointstable.get(day) + cursor.getInt(2));
				}
				else
				{
					dailypointstable.put(day, cursor.getInt(2));
				}
			}while(cursor.moveToNext());
		}
		return dailypointstable;
	}
	

	public void InsertDayPoints(SqlHelper helper, int mon, int day, String type, int points)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(SqlHelper.KEY_DAY, day);
		cv.put(SqlHelper.KEY_TYPE, type);
		cv.put(SqlHelper.KEY_POINTS, points);
		db.insert(Constants.mon[mon - 1], null, cv) ;
		db.close();
	}

	public void UpdateDayPoints(SqlHelper helper, int mon, int day, String type, int points)
	{
		String whereClause = SqlHelper.KEY_DAY + "= ?" + " AND " + SqlHelper.KEY_TYPE + "= ?";
		String[] whereArgs = { Integer.toString(day), type};	
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		//cv.put(SqlHelper.KEY_DAY, day);
		//cv.put(SqlHelper.KEY_TYPE, type);
		cv.put(SqlHelper.KEY_POINTS, points);
		db.update(Constants.mon[this.mon - 1], cv, whereClause, whereArgs) ;
		db.close();
	}

	public boolean GetDayPoints(SqlHelper helper, int mon, int day, String type)
	{
		boolean isExistType = false;
		this.mon = mon;
		this.day = day;
		SQLiteDatabase db = helper.getReadableDatabase();
		String whereClause = SqlHelper.KEY_DAY + "= ?";
		String[] whereArgs = { Integer.toString(day)};
		Cursor cursor = db.query(Constants.mon[this.mon - 1], new String[]{ SqlHelper.KEY_TYPE, SqlHelper.KEY_POINTS }, whereClause, whereArgs, null, null, null);
		this.totaldaypoints = 0;
		cursor.moveToFirst();
		int count = cursor.getCount();
		if(count != 0)
		{
			if(this.type == null)
			{
				this.type = new String[count];
				this.points = new Integer[count];
			}
			int index = 0;
			
			do
			{
				this.type[index] = cursor.getString(0);
				if(this.type[index].equals(type))
				{
					isExistType = true;
				}
				this.points[index] = cursor.getInt(1);
				this.totaldaypoints += this.points[index];
				index++;
			}while(cursor.moveToNext());
		}
		else
		{
			this.type = null;
			this.points = null;
		}
		cursor.close();
		db.close();
		return isExistType;
	}

	
	public int GetDayPoints(SqlHelper helper, int mon, int day)
	{
		this.mon = mon;
		this.day = day;
		int daypoints = 0;
		SQLiteDatabase db = helper.getReadableDatabase();
		String whereClause = SqlHelper.KEY_DAY + "= ?";
		String[] whereArgs = { Integer.toString(day)};
		Cursor cursor = db.query(Constants.mon[this.mon - 1], new String[]{SqlHelper.KEY_POINTS }, whereClause, whereArgs, null, null, null);
		this.totaldaypoints = 0;
		cursor.moveToFirst();
		int count = cursor.getCount();
		if(count != 0)
		{
			do
			{
				daypoints += cursor.getInt(0);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return daypoints;
	}

	public void InsertORUpdateDayPoints(SqlHelper helper, int mon, int day, String type, int points, boolean isTypeExists)
	{	
		if(this.points == null || isTypeExists == false)
		{
			InsertDayPoints(helper, mon, day, type, points);
			this.totaldaypoints += points; //points earned for the day so far
		}
		else
		{
			this.totaldaypoints = 0;
			for(int i =0; i < this.type.length; i++)
			{
				if(this.type[i].equals(type))
				{
					//points = this.points[i] + points;
					this.points[i] = points;
				}
				this.totaldaypoints += this.points[i];
			}
			UpdateDayPoints(helper, mon, day, type, points);
		}
	}
}

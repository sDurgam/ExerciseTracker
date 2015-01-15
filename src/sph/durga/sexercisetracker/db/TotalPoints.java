package sph.durga.sexercisetracker.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TotalPoints 
{
	public TotalPoints() {
		super();
	}

	int points;
	
	public int getPoints() 
	{
		return points;
	}

	public int GetTotalPoints(SqlHelper helper)
	{
		SQLiteDatabase reader = helper.getReadableDatabase();
		Cursor cursor = reader.query(SqlHelper.TABLE_TOTAL_POINTS, new String[]{SqlHelper.KEY_TOTAL_POINTS}, null, null, null, null, null, null);
		cursor.moveToFirst();
		if(cursor.getCount() == 1)
		{
			points = cursor.getInt(0);
		}
		cursor.close();
		reader.close();
		return points;
	}

	public void UpdateTotalPoints(SqlHelper dbHelper, int points, int dbPoints)
	{
		SQLiteDatabase writer = dbHelper.getWritableDatabase();
		this.points = points;
		ContentValues cv = new ContentValues();
		cv.put(SqlHelper.KEY_TOTAL_POINTS, points);
		try
		{
			if(dbPoints == 0)
			{
				writer.insert(SqlHelper.TABLE_TOTAL_POINTS, null, cv);
			}
			else
			{
				writer.update(SqlHelper.TABLE_TOTAL_POINTS, cv, null, null);
			}
		}catch(Exception ex)
		{
			this.points = -1;
		}
	}
}
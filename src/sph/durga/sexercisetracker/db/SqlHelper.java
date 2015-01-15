package sph.durga.sexercisetracker.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper 
{
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "exercisetracker.db";

	public static final String TABLE_JAN_2015 = "jan";
	public static final String TABLE_FEB_2015 = "feb";
	public static final String TABLE_MAR_2015 = "mar";
	public static final String TABLE_APR_2015 = "apr";
	public static final String TABLE_MAY_2015 = "may";
	public static final String TABLE_JUN_2015 = "jun";
	public static final String TABLE_JUL_2015 = "jul";
	public static final String TABLE_AUG_2015 = "aug";
	public static final String TABLE_SEP_2015 = "sep";
	public static final String TABLE_OCT_2015 = "oct";
	public static final String TABLE_NOV_2015 = "nov";
	public static final String TABLE_DEC_2015 = "dec";
	
	public static final String TABLE_TOTAL_POINTS_YY_MM = "totalpointsyearmonth";
	public static final String TABLE_TOTAL_POINTS = "totalpoints";

	public static String KEY_DAY = "day";
	public static String KEY_TYPE = "type";
	public static String KEY_POINTS = "points";

	public static String KEY_TOTAL_POINTS_YEAR = "year";
	public static String KEY_TOTAL_POINTS_MONTH = "mon";
	public static String KEY_TOTAL_POINTS_YY_MM = "points";
	public static String KEY_TOTAL_POINTS = "points";

	
	
	public static String CREATE_JAN_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_JAN_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_FEB_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_FEB_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_MAR_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_MAR_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_APR_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_APR_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_MAY_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_MAY_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_JUN_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_JUN_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_JUL_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_JUL_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_AUG_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_AUG_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_SEP_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_SEP_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_OCT_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_OCT_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_NOV_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_NOV_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_DEC_MONTH_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s TEXT NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_DEC_2015, KEY_DAY, KEY_TYPE, KEY_POINTS, KEY_DAY, KEY_TYPE);
	public static String CREATE_TOTAL_POINTS_YY_MM_TABLE = String.format("CREATE TABLE %s(%s INTEGER NOT NULL, %s INTEGER NOT NULL, %s INTEGER, PRIMARY KEY (%s, %s))", TABLE_TOTAL_POINTS_YY_MM, KEY_TOTAL_POINTS_YEAR, KEY_TOTAL_POINTS_MONTH, KEY_TOTAL_POINTS_YY_MM, KEY_TOTAL_POINTS_YEAR, KEY_TOTAL_POINTS_MONTH); 
	public static String CREATE_TOTAL_POINTS = String.format("CREATE TABLE %s(%s BIGINT PRIMARY KEY NOT NULL)", TABLE_TOTAL_POINTS, KEY_TOTAL_POINTS);
	
	public SqlHelper(Context context, String name, CursorFactory factory,
			int version) 
	{
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	public SqlHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_JAN_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEB_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAR_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_APR_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAY_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_JUN_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_JUL_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUG_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEP_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_OCT_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOV_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEC_2015);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOTAL_POINTS_YY_MM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOTAL_POINTS);
		
		db.execSQL(CREATE_JAN_MONTH_TABLE);
		db.execSQL(CREATE_FEB_MONTH_TABLE);
		db.execSQL(CREATE_MAR_MONTH_TABLE);
		db.execSQL(CREATE_APR_MONTH_TABLE);
		db.execSQL(CREATE_MAY_MONTH_TABLE);
		db.execSQL(CREATE_JUN_MONTH_TABLE);
		db.execSQL(CREATE_JUL_MONTH_TABLE);
		db.execSQL(CREATE_AUG_MONTH_TABLE);
		db.execSQL(CREATE_SEP_MONTH_TABLE);
		db.execSQL(CREATE_OCT_MONTH_TABLE);
		db.execSQL(CREATE_NOV_MONTH_TABLE);
		db.execSQL(CREATE_DEC_MONTH_TABLE);
		db.execSQL(CREATE_TOTAL_POINTS_YY_MM_TABLE);
		db.execSQL(CREATE_TOTAL_POINTS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{

	}
}

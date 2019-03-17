package com.secondassig.androiddatastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataController
{
	public static final String COUNTER="Counter";
	public static final String MESSAGE="Message";
	String[] columns = new String[] { COUNTER, MESSAGE };
	public static final String TABLE_NAME="Msg_Table";
	public static final String DATABASE_NAME="Assignment2.db";
	public static final int DATABASE_VERSION=4;
	public static final String TABLE_CREATE="create table Msg_Table (Counter integer not null, Message text not null);";
	
	DataBaseHelper dbHelper;
	Context context;
	SQLiteDatabase db;
	
	public DataController(Context context)
	{
		this.context=context;
		dbHelper=new DataBaseHelper(context);
	}
	
	public DataController open()
	{
		db=dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public void insert(int counter, String message)
	{
		String sql1 = "insert into " + TABLE_NAME + " (" + COUNTER + ", " + MESSAGE
				+ ") values("+ counter + ", '" + message + "');";

		db.execSQL(sql1);
	}
	
	public Cursor retrieve()
	{
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_NAME;
		Cursor cursor = db.rawQuery(selectQuery, null);
		return cursor;
	}
	
	private static class DataBaseHelper extends SQLiteOpenHelper
	{

		public DataBaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try
			{
				db.execSQL(TABLE_CREATE);
			}
			catch(SQLiteException e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS Msg_Table");
			onCreate(db);
		}
		
	}
	
}
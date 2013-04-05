package com.example.testq2;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class TestDbAdapter {
	
	public static Context sContext;
	public static SQLiteDatabase sDb;
	public static TestHelper mHelper;
	public static TestDbAdapter sInstance;
	
	
	private TestDbAdapter(Context sContext) {
		TestDbAdapter.sContext = sContext;
	}
	
	public static TestDbAdapter createInstance(Context sContext){
		if(sInstance == null){
			sInstance = new TestDbAdapter(sContext);
			open();
		}
		
		return sInstance;
	}

	private static void open() {
		mHelper  = new TestHelper(sContext);
		sDb = mHelper.getWritableDatabase();
		
	}
	private  void close() {
		mHelper.close();
	}
	
	public long inserValue(String name){
	  ContentValues values = new ContentValues();
	  values.put(TestConstant.NAME, name);
	  
	  try{
		  sDb.beginTransaction();
		  final long state   = sDb.insert(TestConstant.TABLE_NAME, null, values);
		  sDb.setTransactionSuccessful();
		  return state;
	  }catch(SQLException e){
		  throw e;
	  }finally{
		  sDb.endTransaction();
	  }
	  
	}

}

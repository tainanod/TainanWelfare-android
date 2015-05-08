package tw.gov.tainan.tainanwelfare.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	private final static int _DBVersion = 1; //<-- ª©¥»
	private final static String _DBName = "welfaredb";	//<-- db name
	private final static String _TableName = "welfaredb"; //<-- table name
	private static DBHelper helper;

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, _DBName, null, _DBVersion);
		// TODO Auto-generated constructor stub
	}
	
	public static synchronized DBHelper getInstance(Context context)
    {
        if(helper == null)
        {
            helper = new DBHelper(context, null, null, 1);
        }

        return helper;
    } 

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		final String SQL1 = "CREATE TABLE LANDMARK (SEQ GUID PRIMARY KEY NOT NULL UNIQUE, " +
				"NAME VARCHAR(1000) NOT NULL COLLATE NOCASE, TOWN VARCHAR(255) NOT NULL COLLATE NOCASE, " +
				"ADDRESS VARCHAR(1000) NOT NULL COLLATE NOCASE, PHONE VARCHAR(50) NOT NULL COLLATE NOCASE, " +
				"LONGITUDE NUMERIC, LATITUDE NUMERIC, SCORE VARCHAR(50) COLLATE NOCASE, " +
				"INFO VARCHAR(4000) COLLATE NOCASE, MARK_TYPE_SEQ INTEGER NOT NULL)";
		final String SQL2 = "CREATE TABLE CATEGORY (SEQ INTEGER PRIMARY KEY NOT NULL UNIQUE, " +
				"CATEGORY_NAME VARCHAR( 255 ) COLLATE NOCASE, UPPER_SEQ INTEGER, VERSION INTEGER )";
		final String SQL3 = "CREATE TABLE NEWS (SEQ GUID PRIMARY KEY NOT NULL UNIQUE, " +
				"TITLE VARCHAR(500) NOT NULL COLLATE NOCASE, CONTENT TEXT NOT NULL COLLATE NOCASE, " +
				"RELEASE_TIME VARCHAR(50), EDIT_TIME VARCHAR, " +
				"CREATOR VARCHAR( 500 ) COLLATE NOCASE, LINK VARCHAR(1000))";
		db.execSQL(SQL1);
		db.execSQL(SQL2);
		db.execSQL(SQL3);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		final String SQL1 = "DROP TABLE LANDMARK";
		final String SQL2 = "DROP TABLE CATEGORY";
		final String SQL3 = "DROP TABLE NEWS";
		db.execSQL(SQL1);
		db.execSQL(SQL2);
		db.execSQL(SQL3);
	}

}

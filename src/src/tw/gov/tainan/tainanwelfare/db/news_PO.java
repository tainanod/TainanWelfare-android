package tw.gov.tainan.tainanwelfare.db;

import java.util.ArrayList;

import tw.gov.tainan.tainanwelfare.dbentity.NewsDBEntity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class news_PO {

	private Context context;
	private DBHelper dbhelper;

	public news_PO(Context context) {
		this.context = context;
		dbhelper = DBHelper.getInstance(context); 
	}
	
	public ArrayList<NewsDBEntity> GetAllNewsList(){
		ArrayList<NewsDBEntity> result = new ArrayList<NewsDBEntity>();
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor c1 = db.rawQuery("SELECT SEQ, TITLE, CONTENT, RELEASE_TIME, EDIT_TIME, " +
				"CREATOR, LINK, strftime('%s', RELEASE_TIME) as stTime  FROM NEWS order by stTime desc", null);
		if(c1.getCount() > 0){
			c1.moveToFirst();
			while(!c1.isAfterLast()){
				NewsDBEntity temp = new NewsDBEntity();
				temp.setSEQ(c1.getString(0));
				temp.setTITLE(c1.getString(1));
				temp.setCONTENT(c1.getString(2));
				temp.setRELEASE_TIME(c1.getString(3));
				temp.setEDIT_TIME(c1.getString(4));
				temp.setCREATOR(c1.getString(5));
				temp.setLINK(c1.getString(6));
				result.add(temp);
				c1.moveToNext();
			}
			c1.close();
			
		}
		return result;
	}
	
	public void InsertNewsList(ArrayList<NewsDBEntity> newslist){
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		for(NewsDBEntity news : newslist){
			ContentValues initialValues = new ContentValues();
			initialValues.put("SEQ", news.getSEQ());
			initialValues.put("TITLE", news.getTITLE());
			initialValues.put("CONTENT", news.getCONTENT());
			initialValues.put("RELEASE_TIME", news.getRELEASE_TIME());
			initialValues.put("EDIT_TIME", news.getEDIT_TIME());
			initialValues.put("CREATOR", news.getCREATOR());
			initialValues.put("LINK", news.getLINK());
			db.replace("NEWS", null, initialValues);
		}
	}
}

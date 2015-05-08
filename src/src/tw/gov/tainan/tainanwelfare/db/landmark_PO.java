package tw.gov.tainan.tainanwelfare.db;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;

import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class landmark_PO {
	
	private Context context;
	private DBHelper dbhelper;

	public landmark_PO(Context context) {
		this.context = context;
		dbhelper = DBHelper.getInstance(context); 
	}

	public ArrayList<LandmarkDBEntity> GetLandmarkList(){
		ArrayList<LandmarkDBEntity> resultList = new ArrayList<LandmarkDBEntity>();
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor c1 = db.rawQuery("SELECT SEQ, NAME, TOWN, ADDRESS, PHONE, " +
				"LONGITUDE, LATITUDE, SCORE, INFO, MARK_TYPE_SEQ FROM LANDMARK", null);
		if(c1.getCount() > 0){
			c1.moveToFirst();
			while(!c1.isAfterLast()){
				LandmarkDBEntity tempobj = new LandmarkDBEntity();
				tempobj.setSEQ(c1.getString(0));
				tempobj.setName(c1.getString(1));
				tempobj.setTown(c1.getString(2));
				tempobj.setAddress(c1.getString(3));
				tempobj.setPhone(c1.getString(4));
				tempobj.setLongitude(c1.getFloat(5));
				tempobj.setLatitude(c1.getFloat(6));
				tempobj.setScore(c1.getString(7));
				if(c1.getString(8) != null){
					HashMap<String, String> info = new HashMap<String, String>();
					try {
						JSONObject jsonobject = new JSONObject(c1.getString(8));
						Iterator<String> keys = jsonobject.keys();
						while(keys.hasNext()){
				            String key = (String)keys.next();
				            info.put(key, jsonobject.getString(key));
				        }
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tempobj.setInfo(info);
				}
				tempobj.setMarkTypeSeq(c1.getInt(9));
				resultList.add(tempobj);
				c1.moveToNext();
			}
			c1.close();
		}
		return resultList;
	}

	public ArrayList<LandmarkDBEntity> GetSearchLandmarkList(String SearchStr){
		ArrayList<LandmarkDBEntity> resultList = new ArrayList<LandmarkDBEntity>();
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor c1 = db.rawQuery("SELECT SEQ, NAME, TOWN, ADDRESS, PHONE, " +
				"LONGITUDE, LATITUDE, SCORE, INFO, MARK_TYPE_SEQ FROM LANDMARK " +
				"WHERE NAME like ? OR ADDRESS like ? OR PHONE like ? OR INFO like ? ", 
				new String[] { "%" + SearchStr + "%", "%" + SearchStr + "%",
							   "%" + SearchStr + "%", "%" + SearchStr + "%" });
		if(c1.getCount() > 0){
			c1.moveToFirst();
			while(!c1.isAfterLast()){
				LandmarkDBEntity tempobj = new LandmarkDBEntity();
				tempobj.setSEQ(c1.getString(0));
				tempobj.setName(c1.getString(1));
				tempobj.setTown(c1.getString(2));
				tempobj.setAddress(c1.getString(3));
				tempobj.setPhone(c1.getString(4));
				tempobj.setLongitude(c1.getFloat(5));
				tempobj.setLatitude(c1.getFloat(6));
				tempobj.setScore(c1.getString(7));
				HashMap<String, String> info = new HashMap<String, String>();
				try {
					JSONObject jsonobject = new JSONObject(c1.getString(8));
					Iterator<String> keys = jsonobject.keys();
					while(keys.hasNext()){
			            String key = (String)keys.next();
			            info.put(key, jsonobject.getString(key));
			        }
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tempobj.setInfo(info);
				tempobj.setMarkTypeSeq(c1.getInt(9));
				resultList.add(tempobj);
				c1.moveToNext();
			}
			c1.close();
		}
		return resultList;
	}
	
	public ArrayList<LandmarkDBEntity> GetOptionLandmarkList(String OptionStr){
		ArrayList<LandmarkDBEntity> resultList = new ArrayList<LandmarkDBEntity>();
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor c1 = db.rawQuery("SELECT SEQ, NAME, TOWN, ADDRESS, PHONE, " +
					"LONGITUDE, LATITUDE, SCORE, INFO, MARK_TYPE_SEQ FROM LANDMARK " +
					"WHERE MARK_TYPE_SEQ IN (?)", new String[] { OptionStr });
		if(c1.getCount() > 0){
			c1.moveToFirst();
			while(!c1.isAfterLast()){
				LandmarkDBEntity tempobj = new LandmarkDBEntity();
				tempobj.setSEQ(c1.getString(0));
				tempobj.setName(c1.getString(1));
				tempobj.setTown(c1.getString(2));
				tempobj.setAddress(c1.getString(3));
				tempobj.setPhone(c1.getString(4));
				tempobj.setLongitude(c1.getFloat(5));
				tempobj.setLatitude(c1.getFloat(6));
				tempobj.setScore(c1.getString(7));
				HashMap<String, String> info = new HashMap<String, String>();
				try {
					JSONObject jsonobject = new JSONObject(c1.getString(8));
					Iterator<String> keys = jsonobject.keys();
					while(keys.hasNext()){
			            String key = (String)keys.next();
			            info.put(key, jsonobject.getString(key));
			        }
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tempobj.setInfo(info);
				tempobj.setMarkTypeSeq(c1.getInt(9));
				resultList.add(tempobj);
				c1.moveToNext();
			}
			c1.close();
		}
		return resultList;
	}
	
	public ArrayList<LandmarkDBEntity> GetOptionSearchLandmarkList(String OptionStr, String SearchStr){
		ArrayList<LandmarkDBEntity> resultList = new ArrayList<LandmarkDBEntity>();
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor c1 = null;
		if(SearchStr.isEmpty()){
			c1 = db.rawQuery("SELECT SEQ, NAME, TOWN, ADDRESS, PHONE, " +
					"LONGITUDE, LATITUDE, SCORE, INFO, MARK_TYPE_SEQ FROM LANDMARK " +
					"WHERE MARK_TYPE_SEQ IN ( " + OptionStr + " )", null);
		}
		else{
			c1 = db.rawQuery("SELECT SEQ, NAME, TOWN, ADDRESS, PHONE, " +
					"LONGITUDE, LATITUDE, SCORE, INFO, MARK_TYPE_SEQ FROM LANDMARK " +
					"WHERE MARK_TYPE_SEQ IN ( " + OptionStr + " ) AND (NAME like ? OR ADDRESS like ? OR PHONE like ? OR INFO like ? )"
					, new String[] { "%" + SearchStr + "%", "%" + SearchStr + "%",
					                            "%" + SearchStr + "%", "%" + SearchStr + "%" });
		}
		if(c1.getCount() > 0){
			c1.moveToFirst();
			while(!c1.isAfterLast()){
				LandmarkDBEntity tempobj = new LandmarkDBEntity();
				tempobj.setSEQ(c1.getString(0));
				tempobj.setName(c1.getString(1));
				tempobj.setTown(c1.getString(2));
				tempobj.setAddress(c1.getString(3));
				tempobj.setPhone(c1.getString(4));
				tempobj.setLongitude(c1.getFloat(5));
				tempobj.setLatitude(c1.getFloat(6));
				tempobj.setScore(c1.getString(7));
				HashMap<String, String> info = new HashMap<String, String>();
				try {
					JSONObject jsonobject = new JSONObject(c1.getString(8));
					Iterator<String> keys = jsonobject.keys();
					while(keys.hasNext()){
			            String key = (String)keys.next();
			            info.put(key, jsonobject.getString(key));
			        }
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tempobj.setInfo(info);
				tempobj.setMarkTypeSeq(c1.getInt(9));
				resultList.add(tempobj);
				c1.moveToNext();
			}
			c1.close();
		}
		return resultList;
	}
	
	public void InsertLandmark(LandmarkDBEntity entity){
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("SEQ", entity.getSEQ());
		values.put("NAME", entity.getName());
		values.put("TOWN", entity.getTown());
		values.put("ADDRESS", entity.getAddress());
		values.put("PHONE", entity.getPhone());
		values.put("LONGITUDE", entity.getLongitude());
		values.put("LATITUDE", entity.getLatitude());
		values.put("SCORE", entity.getScore());
		Gson gson = new GsonBuilder().create();
		String jsonString = gson.toJson(entity.getInfo());
		values.put("INFO", jsonString);
		values.put("MARK_TYPE_SEQ", entity.getMarkTypeSeq());
		db.replace("LANDMARK", null, values);
	}
}

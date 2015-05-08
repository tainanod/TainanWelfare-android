package tw.gov.tainan.tainanwelfare.dbentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ODJsonParser {

	public ArrayList<CategoryDBEntity> ParseToCategoryList(String sourcestr)
			throws JSONException {
		ArrayList<CategoryDBEntity> resultList = new ArrayList<CategoryDBEntity>();
		JSONArray jarray = new JSONArray(sourcestr);
		for (int i = 0; i < jarray.length(); i++) {
			CategoryDBEntity entity = new CategoryDBEntity();
			JSONObject jObject = jarray.getJSONObject(i);
			entity.setSeq(jObject.getInt("SEQ"));
			entity.setCategoryName(jObject.getString("CATEGORY_NAME"));
			entity.setUpperSeq(jObject.getInt("UPPER_SEQ"));
			entity.setVersion(jObject.getInt("VERSION"));
			resultList.add(entity);
		}
		return resultList;
	}

	public ArrayList<LandmarkDBEntity> ParseToLandmarkList(String sourcestr)
			throws JSONException {
		ArrayList<LandmarkDBEntity> resultList = new ArrayList<LandmarkDBEntity>();
		JSONArray jarray = new JSONArray(sourcestr);
		for (int i = 0; i < jarray.length(); i++) {
			JSONObject jObject = jarray.getJSONObject(i);
			LandmarkDBEntity landmark = new LandmarkDBEntity();
			landmark.setSEQ(jObject.getString("SEQ"));
			landmark.setName(jObject.getString("NAME"));
			landmark.setAddress(jObject.getString("ADDRESS"));
			landmark.setTown(jObject.getString("TOWN"));
			landmark.setLongitude((float) jObject.getDouble("LONGITUDE"));
			landmark.setLatitude((float) jObject.getDouble("LATITUDE"));
			landmark.setPhone(jObject.getString("PHONE"));
			landmark.setScore(jObject.getString("SCORE"));
			String infostr = jObject.getString("INFO");
			if (infostr.length() < 0) {
				String newstring = jObject.getString("INFO")
						.replace("\'", "\"");
				JSONObject newobj = new JSONObject(newstring);
				Iterator<String> keys = newobj.keys();
				HashMap<String, String> info = new HashMap<String, String>();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					info.put(key, newobj.getString(key));
				}
				landmark.setInfo(info);
			}
			landmark.setMarkTypeSeq(jObject.getInt("CATEGORY_SEQ"));
			resultList.add(landmark);
		}
		return resultList;
	}

	public static HashMap<String, String> ConvertJsonStrToHashMap(
			String JsonString) {
		HashMap<String, String> info = new HashMap<String, String>();
		try {
			JSONObject jsonobject = new JSONObject(JsonString);
			Iterator<String> keys = jsonobject.keys();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				info.put(key, jsonobject.getString(key));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}

	public static ArrayList<NewsDBEntity> ParseToNewsList(String JsonString) {
		ArrayList<NewsDBEntity> resultList = new ArrayList<NewsDBEntity>();
		try {
			JSONArray jarray = new JSONArray(JsonString);
			for (int i = 0; i < jarray.length(); i++) {

				JSONObject jObject = jarray.getJSONObject(i);
				NewsDBEntity newsEntity = new NewsDBEntity();
				newsEntity.setSEQ(jObject.getString("ID"));
				newsEntity.setCREATOR("TainanWebRss");
				newsEntity.setCONTENT(jObject.getString("DESCRIPTION"));
				newsEntity.setRELEASE_TIME(jObject.getString("PUB_DATE"));
				newsEntity.setEDIT_TIME(jObject.getString("PUB_DATE"));
				newsEntity.setTITLE(jObject.getString("TITLE"));
				newsEntity.setLINK(jObject.getString("LINK"));
				resultList.add(newsEntity);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}
}

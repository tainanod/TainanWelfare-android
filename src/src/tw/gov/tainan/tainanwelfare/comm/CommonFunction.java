package tw.gov.tainan.tainanwelfare.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;

public class CommonFunction {
	
	public static Bitmap GetBitmapByPath(String bitmapfilePath, int inSampleSize){
		try {
			InputStream is = null;
			File file = new File(bitmapfilePath);
			is = new FileInputStream(file);
			Bitmap bmp = BitmapFactory.decodeStream(is, null, getBitmapOptions(inSampleSize));
			return bmp;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	public static BitmapFactory.Options getBitmapOptions(int inSampleSize){
		BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inPurgeable = true;
	    options.inInputShareable = true;
	    options.inSampleSize = inSampleSize;
	    return options;
	}
	
	public static boolean checkInternetConnection(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (cm.getActiveNetworkInfo() != null) {
			if (cm.getActiveNetworkInfo().isAvailable()
					&& cm.getActiveNetworkInfo().isConnected()) {
				return true;
			} 
			else {
				return false;
			}
		} 
		else {
			return false;
		}
	}
	
	//經緯度轉換距離公式
	public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
	    double earthRadius = 3958.75;
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    int meterConversion = 1609;

	    return (dist * meterConversion) / 1000;
	}
	
	public static float dpFromPx(Context context, float px)
	{
	    return px / context.getResources().getDisplayMetrics().density;
	}

	public static float pxFromDp(Context context, float dp)
	{
	    return dp * context.getResources().getDisplayMetrics().density;
	}
	
	public static LandmarkDBEntity GetNearestLandmark(ArrayList<LandmarkDBEntity> list){
		LandmarkDBEntity result = new LandmarkDBEntity();
		double minDistance = 5000000;
		for(LandmarkDBEntity marker : list){
			double distance = distFrom(Util.currentLatitude, Util.currentLongitude, 
					marker.getLatitude(), marker.getLongitude());
			if(distance < minDistance){
				minDistance = distance;
				result = marker;
			}
		}
		return result;
	}
}

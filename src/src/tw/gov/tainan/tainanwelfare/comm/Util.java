package tw.gov.tainan.tainanwelfare.comm;

import com.google.android.gms.maps.model.BitmapDescriptor;

import tw.gov.tainan.tainanwelfare.R;

public class Util {
	
	public static double currentLongitude = 120.1893272; 
	public static double currentLatitude = 23.012297;
	public static int TIME_OUT = 30000;
	public static String WebServiceUrl = "http://210.69.40.35/app/api/";
	public static String NewsRSSUrl = "http://210.69.40.35/app/api/rssnews";
	
	public static double tainanAreaTop = 120.5110271;
	public static double tainanAreaButtom = 120.029968;
	public static double tainanAreaRight = 23.402198;
	public static double tainanAreaLeft = 22.919111;
	public static double tainanTransationLat = 22.997144;
	public static double tainanTransationLng = 120.212966; 
	
	public static int[] markerIconList = new int[] { R.drawable.location01, R.drawable.location02,
			   R.drawable.location03, R.drawable.location04,
			   R.drawable.location05, R.drawable.location06,
			   R.drawable.location07, R.drawable.location08,
			   R.drawable.location09};
}

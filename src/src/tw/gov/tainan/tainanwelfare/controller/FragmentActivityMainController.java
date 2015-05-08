package tw.gov.tainan.tainanwelfare.controller;

import java.util.ArrayList;

import tw.gov.tainan.tainanwelfare.FragmentActivityMain;
import tw.gov.tainan.tainanwelfare.db.landmark_PO;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import tw.gov.tainan.tainanwelfare.service.GPSTracker;
import tw.gov.tainan.tainanwelfare.task.GetOptionLandmarkListDataTask;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

public class FragmentActivityMainController {

	private Context context;
	private FragmentActivityMain activity;
	private GPSTracker tracker;
	
	public FragmentActivityMainController(Activity activity, Context context){
		this.activity = (FragmentActivityMain)activity;
		this.context = context;
		tracker = new GPSTracker(context);
	}
	
	public void ExecOptionLandmarkListDataTask(String OptionStr, String SearchStr){
		new GetOptionLandmarkListDataTask(activity, context, OptionStr, SearchStr).execute();
	}
	
	public void startGPS(){
		tracker.startUsingGPS();
	}
	
	public void stopGPS(){
		tracker.stopUsingGPS();
	}
}

package tw.gov.tainan.tainanwelfare.task;

import java.util.ArrayList;

import tw.gov.tainan.tainanwelfare.comm.InterfaceGetLandmark;
import tw.gov.tainan.tainanwelfare.db.landmark_PO;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

public class GetOptionLandmarkListDataTask extends AsyncTask<String, Integer, ArrayList<LandmarkDBEntity>>{

	private Activity activity;
	private Context context;
	private landmark_PO landmarkPO;
	private String Option;
	private String SearchStr;
	private InterfaceGetLandmark interfaceActivity;
	
	public GetOptionLandmarkListDataTask(Activity activity, Context context, String Option, String SearchStr){
		this.context = context;
		landmarkPO = new landmark_PO(context);
		this.Option = Option;
		this.SearchStr = SearchStr;
		this.activity = activity;
		this.interfaceActivity = (InterfaceGetLandmark)activity;
	}
	
	@Override
	protected ArrayList<LandmarkDBEntity> doInBackground(String... params) {
		// TODO Auto-generated method stub
		ArrayList<LandmarkDBEntity> result = landmarkPO.GetOptionSearchLandmarkList(Option, SearchStr);
		return result;
	}
	
	@Override
	protected void onPostExecute(ArrayList<LandmarkDBEntity> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		interfaceActivity.afterGetLandmarkDataList(result);
	}
}

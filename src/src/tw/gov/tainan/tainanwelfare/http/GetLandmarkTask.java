package tw.gov.tainan.tainanwelfare.http;

import java.util.ArrayList;

import org.json.JSONException;

import tw.gov.tainan.tainanwelfare.MainActivity;
import tw.gov.tainan.tainanwelfare.db.landmark_PO;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import android.content.Context;
import android.os.AsyncTask;

public class GetLandmarkTask extends AsyncTask<String, Integer, ArrayList<LandmarkDBEntity>>{

	private Context context;
	private int CategorySeq;
	private landmark_PO landmarkPO;
	private boolean isGoToMenu = false;
	private MainActivity activity;
	
	public GetLandmarkTask(MainActivity activity, Context context, int CategorySeq, boolean isGoToMenu){
		this.context = context;
		this.CategorySeq = CategorySeq;
		landmarkPO = new landmark_PO(context);
		this.isGoToMenu = isGoToMenu;
		this.activity = activity;
	}
	
	@Override
	protected ArrayList<LandmarkDBEntity> doInBackground(String... params) {
		// TODO Auto-generated method stub
		ArrayList<LandmarkDBEntity> resultList = new ArrayList<LandmarkDBEntity>();
		WebApiFunction webapifunction = new WebApiFunction();
		try {
			resultList = webapifunction.GetCategoryLandmark(CategorySeq);
			for(LandmarkDBEntity entity : resultList){
				landmarkPO.InsertLandmark(entity);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultList;
	}
	
	@Override
	protected void onPostExecute(ArrayList<LandmarkDBEntity> result) {
		// TODO Auto-generated method stub
		if(isGoToMenu){
			activity.GoToFunctionMenu();
		}
	}
}

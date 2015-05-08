package tw.gov.tainan.tainanwelfare.http;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONException;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.MainActivity;
import tw.gov.tainan.tainanwelfare.db.category_PO;
import tw.gov.tainan.tainanwelfare.dbentity.CategoryDBEntity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

public class GetCategoryTask extends AsyncTask<String, Integer, ArrayList<CategoryDBEntity>>{

	private MainActivity activity;
	private Context context;
	private category_PO categoryPO;
    private static ExecutorService SINGLE_TASK_EXECUTOR;
    private ArrayList<CategoryDBEntity> result = new ArrayList<CategoryDBEntity>();
    private ArrayList<CategoryDBEntity> UpdateCategoryList = new ArrayList<CategoryDBEntity>();
	
	public GetCategoryTask(MainActivity activity, Context context){
		this.activity = activity;
		this.context = context;
		categoryPO = new category_PO(context);
		SINGLE_TASK_EXECUTOR = (ExecutorService)Executors.newSingleThreadExecutor();
	}
	
	@Override
	protected ArrayList<CategoryDBEntity> doInBackground(String... params) {
		// TODO Auto-generated method stub
		WebApiFunction webapifunction = new WebApiFunction();
		
		try {
			result = webapifunction.GetAllCategory();
			
			for(int i = 0; i < result.size(); i++){
				CategoryDBEntity webObject = result.get(i);
				CategoryDBEntity nowObject = categoryPO.GetCategory(webObject.getSeq());
				if(nowObject != null){
					if(webObject.getVersion() > nowObject.getVersion()){
						categoryPO.InsertCategory(webObject);
						UpdateCategoryList.add(webObject);
					}
				}
				else{
					categoryPO.InsertCategory(webObject);
					UpdateCategoryList.add(webObject);
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	protected void onPostExecute(ArrayList<CategoryDBEntity> result) {
		// TODO Auto-generated method stub
		if(UpdateCategoryList.size() > 0){
			for(int i = 0; i < UpdateCategoryList.size(); i++){
				CategoryDBEntity object = UpdateCategoryList.get(i); 
				boolean isLast = (i == (UpdateCategoryList.size() - 1));
				new GetLandmarkTask(activity, context, object.getSeq(), isLast).executeOnExecutor(SINGLE_TASK_EXECUTOR, null);
			}
		}
		else{
			new Handler().postDelayed(new Runnable(){
			    public void run() {
			    //execute the task
			    	activity.GoToFunctionMenu();
			    }
			}, 1000);
		}
	}
	
}

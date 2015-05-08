package tw.gov.tainan.tainanwelfare.controller;

import java.util.ArrayList;

import tw.gov.tainan.tainanwelfare.ActivityNews;
import tw.gov.tainan.tainanwelfare.MainActivity;
import tw.gov.tainan.tainanwelfare.db.landmark_PO;
import tw.gov.tainan.tainanwelfare.db.news_PO;
import tw.gov.tainan.tainanwelfare.dbentity.NewsDBEntity;
import android.content.Context;
import android.os.AsyncTask;

public class ActivityNewsController {
	
	public ActivityNews activity;
	public Context context;
	
	public ActivityNewsController(ActivityNews activity, Context context){
		this.context = context;
		this.activity = activity;
	}
	
	public void ExecNewsListTask(){
		new GetNewsListTask(context).execute();
	}
	
	private class GetNewsListTask extends AsyncTask<String, Integer, ArrayList<NewsDBEntity>>{
		
		private Context context;
		private news_PO newsPO;
		
		public GetNewsListTask(Context context){
			this.context = context;
			newsPO = new news_PO(context);
		}

		@Override
		protected ArrayList<NewsDBEntity> doInBackground(String... params) {
			// TODO Auto-generated method stub
			ArrayList<NewsDBEntity> result = newsPO.GetAllNewsList();
			return result;
		}
		
		@Override
		protected void onPostExecute(ArrayList<NewsDBEntity> result) {
			// TODO Auto-generated method stub
			activity.ShowNewsList(result);
		}
		
	}

}

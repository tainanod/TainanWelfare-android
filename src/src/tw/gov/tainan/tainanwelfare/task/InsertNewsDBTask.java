package tw.gov.tainan.tainanwelfare.task;

import java.util.ArrayList;

import tw.gov.tainan.tainanwelfare.db.news_PO;
import tw.gov.tainan.tainanwelfare.dbentity.NewsDBEntity;
import android.content.Context;
import android.os.AsyncTask;

public class InsertNewsDBTask extends AsyncTask<String, Integer, String>{

	private news_PO newsPO;
	private Context context;
	private ArrayList<NewsDBEntity> newsList;
	
	public InsertNewsDBTask(Context context, ArrayList<NewsDBEntity> newsList){
		this.context = context;
		newsPO = new news_PO(context);
		this.newsList = newsList;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		newsPO.InsertNewsList(newsList);
		return null;
	}

}

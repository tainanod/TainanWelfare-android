package tw.gov.tainan.tainanwelfare.task;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import tw.gov.tainan.tainanwelfare.dbentity.NewsDBEntity;
import tw.gov.tainan.tainanwelfare.http.WebApiFunction;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class RSSDataParserTask extends
		AsyncTask<String, Integer, ArrayList<NewsDBEntity>> {

	private String tempContent;
	private Context context;
	private Object res;
	

	public RSSDataParserTask(Context context) {
		this.context = context;
	}

	@Override
	protected ArrayList<NewsDBEntity> doInBackground(String... params) {
		// TODO Auto-generated method stub
		ArrayList<NewsDBEntity> postDataList = new ArrayList<NewsDBEntity>();
		try {
			WebApiFunction webApiFunction = new WebApiFunction();
			postDataList = webApiFunction.GetNewsList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("RSSParser", e.getMessage());
		}
		return postDataList;
	}

	@Override
	protected void onPostExecute(ArrayList<NewsDBEntity> result) {
		// TODO Auto-generated method stub
		new InsertNewsDBTask(context, result).execute();
	}
}

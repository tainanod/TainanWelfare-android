package tw.gov.tainan.tainanwelfare;

import java.util.ArrayList;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.adapter.NewsItemAdapter;
import tw.gov.tainan.tainanwelfare.controller.ActivityNewsController;
import tw.gov.tainan.tainanwelfare.dbentity.NewsDBEntity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ActivityNews extends Activity {

	private ViewFlipper VFNewsList;
	private ListView listnews;
	private TextView ndTitle;
	private TextView ndTime;
	private TextView ndSource;
	private TextView ndedittime;
	private WebView ndContent;
	private ScrollView ScrollView;
	private NewsItemAdapter newlistAdapter;
	private ArrayList<NewsDBEntity> objects = new ArrayList<NewsDBEntity>();
	private ActivityNewsController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		controller = new ActivityNewsController(ActivityNews.this, getApplicationContext());
		
		//自訂ActionBar
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_custom);
		TextView tvTitle = (TextView)findViewById(R.id.CustomActionBarTitle);
		tvTitle.setText("最新消息");
		
		ScrollView = (ScrollView)findViewById(R.id.scrollview);
		VFNewsList = (ViewFlipper)findViewById(R.id.VFNewsList);
		listnews = (ListView)findViewById(R.id.listnews);
		ndTitle = (TextView)findViewById(R.id.ndTitle);
		ndTime = (TextView)findViewById(R.id.ndTime);
		ndSource = (TextView)findViewById(R.id.ndSource);
		ndedittime = (TextView)findViewById(R.id.ndedittime);
		ndContent = (WebView)findViewById(R.id.ndContent);
		ndContent.getSettings().setJavaScriptEnabled(true);
		
		VFNewsList.setDisplayedChild(0);
		
		listnews.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				VFNewsList.setDisplayedChild(1);
				final NewsDBEntity target = objects.get(position);
				ndTitle.setText(target.getTITLE());
				ndTime.setText(target.getRELEASE_TIME());
				ndSource.setText(target.getCREATOR());
				ndedittime.setText(target.getEDIT_TIME());
				ndContent.loadDataWithBaseURL("", target.getCONTENT(), "text/html", "UTF-8", "");
				
				ScrollView.post(new Runnable() {
				    @Override
				    public void run() {
				    	ScrollView.scrollTo(0, 0);
				    } 
				});
				
				ndTitle.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent ie = new Intent(Intent.ACTION_VIEW,Uri.parse(target.getLINK()));
				        startActivity(ie);
					}
				});
			}
		});
		
		//執行去DB取得NewsList的Task
		controller.ExecNewsListTask();
	}
	
	//按下返回鍵，ViewPaper返回一層。
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		int index = VFNewsList.getDisplayedChild();
		if (index > 0) {
			VFNewsList.setDisplayedChild(index-1);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void ShowNewsList(ArrayList<NewsDBEntity> DBobjects){
		objects = DBobjects;
		newlistAdapter = new NewsItemAdapter(ActivityNews.this, DBobjects);
		listnews.setAdapter(newlistAdapter);
		newlistAdapter.notifyDataSetChanged();
	}
	
}

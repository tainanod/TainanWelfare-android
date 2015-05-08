package tw.gov.tainan.tainanwelfare;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.comm.CommonFunction;
import tw.gov.tainan.tainanwelfare.controller.loadingcontroller;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

//本頁是Loading頁，過500ms跳轉到主功能頁
public class MainActivity extends Activity {

	private Context context;
	private MainActivity activity;
	private loadingcontroller controller;
	private ImageView IVLoadingBG;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.context = getApplicationContext();
		this.activity = MainActivity.this;
		this.controller = new loadingcontroller(MainActivity.this, context);
		controller.DBInit();
		
		//判斷是否有網路，若沒有網路，則三秒過後導入功能頁。若有網路，則呼叫Web Api。
		if(CommonFunction.checkInternetConnection(getApplicationContext())){
			controller.ExecGetCategoryAndLandMarkTask();
			controller.ExecGetNewsListTask();
		}
		else{
			new Handler().postDelayed(new Runnable(){
			    public void run() {
			    //execute the task
			    	Toast.makeText(MainActivity.this, getString(R.string.loading), Toast.LENGTH_SHORT).show();
			    	GoToFunctionMenu();
			    }
			}, 3000);
		}	
	}
	
	//轉導主功能頁面。
	public void GoToFunctionMenu(){
		Intent intent = new Intent(MainActivity.this, ActivityFunctionMenu.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		MainActivity.this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

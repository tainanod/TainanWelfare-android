package tw.gov.tainan.tainanwelfare.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import tw.gov.tainan.tainanwelfare.MainActivity;
import tw.gov.tainan.tainanwelfare.comm.Util;
import tw.gov.tainan.tainanwelfare.http.GetCategoryTask;
import tw.gov.tainan.tainanwelfare.service.GPSTracker;
import tw.gov.tainan.tainanwelfare.task.RSSDataParserTask;

public class loadingcontroller {
	
	public MainActivity activity;
	public Context context;
	
	public loadingcontroller(MainActivity activity, Context context){
		this.context = context;
		this.activity = activity;
	}
	
	public void DBInit() {
		File f = new File("/data/data/tw.gov.tainan.tainanwelfare/databases/welfaredb");
		File dir = new File("/data/data/tw.gov.tainan.tainanwelfare/databases");
		if (!f.exists()) {
			try {
				dir.mkdirs();
				String DB_NAME = "welfaredb";
				InputStream input = context.getAssets().open(DB_NAME);
				OutputStream output = new FileOutputStream(
						"/data/data/tw.gov.tainan.tainanwelfare/databases/welfaredb");
				byte[] buffer = new byte[1024];
				int length;
				while ((length = input.read(buffer)) > 0) {
					output.write(buffer, 0, length);
				}
				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ExecGetCategoryAndLandMarkTask(){
		new GetCategoryTask(activity, context).execute();
	}

	public void ExecGetNewsListTask(){
		new RSSDataParserTask(context).execute();
	}
}

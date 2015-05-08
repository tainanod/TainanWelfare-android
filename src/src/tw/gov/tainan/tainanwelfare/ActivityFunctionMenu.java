package tw.gov.tainan.tainanwelfare;

import com.google.android.gms.internal.ax;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.comm.CommonFunction;
import tw.gov.tainan.tainanwelfare.comm.Util;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

//�������D�\�୶��
public class ActivityFunctionMenu extends Activity{
	
	private Button BTNCareTempMap;
	private Button BTNSweetTempMap;
	private Button BTNMedTempMap;
	private Button BTNNewTemp;
	private Button btnAppIntroduction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_functionmenu);
		
		BTNCareTempMap = (Button)findViewById(R.id.BTNCareTempMap);
		BTNSweetTempMap = (Button)findViewById(R.id.BTNSweetTempMap);
		BTNMedTempMap = (Button)findViewById(R.id.BTNMedTempMap);
		BTNNewTemp = (Button)findViewById(R.id.BTNNewTemp);
		btnAppIntroduction = (Button)findViewById(R.id.btnAppInfomation);
		
		btnAppIntroduction.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ActivityFunctionMenu.this, ActivityAppIntroduction.class);
				startActivity(intent);
			}
		});
		
		//�Y�S���}��GPS�A�h���X��������
		LocationManager locationManager = (LocationManager) getApplication().getSystemService(LOCATION_SERVICE);
		if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			AlertDialog.Builder builder = new AlertDialog.Builder(ActivityFunctionMenu.this);
			builder.setTitle("�t�δ���");
			builder.setMessage("�ثe�L�k�w��A�Х��}��GPS�A�|����Ϊ�APP����");
			builder.setNeutralButton("�T�{", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			builder.show();
		}
		
		//Demo�ΡA���᭫�s�ק� Hank@20140326
		BTNCareTempMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//�a�вM��
				Intent intent = new Intent(ActivityFunctionMenu.this, FragmentActivityMain.class);
				intent.putExtra("landmarkType", 2);
				startActivity(intent);
			}
		});
		
		BTNSweetTempMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//�a�вM��
				Intent intent = new Intent(ActivityFunctionMenu.this, FragmentActivityMain.class);
				intent.putExtra("landmarkType", 1);
				startActivity(intent);
			}
		});
		
		BTNMedTempMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//�a�вM��
				Intent intent = new Intent(ActivityFunctionMenu.this, FragmentActivityMain.class);
				intent.putExtra("landmarkType", 3);
				startActivity(intent);
			}
		});
		
		BTNNewTemp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ActivityFunctionMenu.this, ActivityNews.class);
				startActivity(intent);
			}
		});
	}

}

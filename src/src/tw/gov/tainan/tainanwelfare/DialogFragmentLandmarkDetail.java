package tw.gov.tainan.tainanwelfare;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.comm.Util;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

//本Class為地標詳細對話框。
@SuppressLint("ValidFragment")
public class DialogFragmentLandmarkDetail extends DialogFragment{

	private Activity activity;
	private Context context;
	private LandmarkDBEntity data;
	private TextView TVName;
	private TextView TVScore;
	private TextView TVAddress;
	private TextView TVPhone;
	private TextView TVInfo;
	private ImageView IVLoaction;
	private ImageView IVNavi;
	private ImageView IVhPhone;
	private ImageView IVClose;
	private boolean disableIVLoaction;
	
	
	public DialogFragmentLandmarkDetail(LandmarkDBEntity data, Activity activity, Context context, boolean disableIVLoaction){
		this.data = data;
		this.activity = activity;
		this.context = context;
		this.disableIVLoaction = disableIVLoaction;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		View rootView = inflater.inflate(R.layout.dialogfragment_landmarkdetail, container);
		TVName = (TextView) rootView.findViewById(R.id.TVName);
		TVScore = (TextView) rootView.findViewById(R.id.TVScore);
		TVAddress = (TextView) rootView.findViewById(R.id.TVAddress);
		TVPhone = (TextView) rootView.findViewById(R.id.TVPhone);
		TVInfo = (TextView) rootView.findViewById(R.id.TVInfo);
		IVLoaction = (ImageView) rootView.findViewById(R.id.IVLoaction);
		IVNavi = (ImageView) rootView.findViewById(R.id.IVNavi);
		IVhPhone = (ImageView) rootView.findViewById(R.id.IVhPhone);
		IVClose = (ImageView) rootView.findViewById(R.id.IVClose);
		TVName.setText(data.getName());
		if(data.getScore().length() > 0){
			TVScore.setText("評等：" + data.getScore());
		}
		else{
			TVScore.setVisibility(View.GONE);
		}
		TVAddress.setText("地址：" + data.getAddress());
		
		if(!data.getPhone().equalsIgnoreCase("null")){
			TVPhone.setText("電話:" + data.getPhone());
			//撥通電話按鈕
			IVhPhone.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getDialog().dismiss();
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + data.getPhone()));
					startActivity(intent);
				}
			});
		}
		else{
			TVPhone.setVisibility(View.GONE);
			//撥通電話按鈕
			IVhPhone.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					AlertDialog.Builder builder = new AlertDialog.Builder(activity);
					builder.setTitle("系統提醒");
					builder.setMessage("無法撥打電話，該單位尚未提供電話資訊");
					builder.setNeutralButton("確認", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					builder.show();
				}
			});
		}
		
		StringBuilder string1 = new StringBuilder();
		if(data.getInfo() != null){
			for(String key : data.getInfo().keySet()){
				string1.append(key + ": " + data.getInfo().get(key) + "\n");
			}
			TVInfo.setText(string1);
		}
		
		//關閉按鈕
		IVClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getDialog().dismiss();
			}
		});
		
		
		
		//導航按鈕
		IVNavi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getDialog().dismiss();
				String label = data.getName(); 
				String uriBegin = "geo:" + Util.currentLatitude + "," + Util.currentLongitude; 
				String query = data.getLatitude() + "," + data.getLongitude() + "(" + label + ")"; 
				String encodedQuery = Uri.encode(query); 
				String uriString = uriBegin + "?q=" + encodedQuery + "&z=16"; 
				Uri uri = Uri.parse(uriString); 
				Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri); 
				startActivity(mapIntent);
													
			}
		});
		
		//定位按鈕
		if(disableIVLoaction){
			IVLoaction.setBackgroundResource(R.drawable.detailedicon_01disable);
			IVLoaction.setOnClickListener(null);
		}
		else{
			IVLoaction.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					getDialog().dismiss();
					Intent intent = new Intent(activity, FragemtActivityLandmarkMap.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("data", data);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
		}
		return rootView;
	}
}

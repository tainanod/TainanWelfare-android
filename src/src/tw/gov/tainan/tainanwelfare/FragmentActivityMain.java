package tw.gov.tainan.tainanwelfare;

import java.util.ArrayList;
import java.util.HashMap;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.comm.CommonFunction;
import tw.gov.tainan.tainanwelfare.comm.InterfaceGetLandmark;
import tw.gov.tainan.tainanwelfare.comm.InterfaceUpdateFragment;
import tw.gov.tainan.tainanwelfare.controller.FragmentActivityMainController;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

//本Class是顯示照顧地標的地標清單頁
public class FragmentActivityMain extends Activity implements InterfaceGetLandmark{

	private FragmentActivityMain activity;
	private Context context;
	private InterfaceUpdateFragment currentFragment;
	
	private FragmentActivityMainController controller;
	private LinearLayout llOptionBtn;
	private EditText ETSearch;
	private Button BtnMap;
	private boolean[] IsShowArray = { false, false, false, false, 
									  false, false, false, false, false}; 
	private int[] IconArray = { R.drawable.icon_01, R.drawable.icon_02, 
			                    R.drawable.icon_03, R.drawable.icon_04,
			                    R.drawable.icon_05, R.drawable.icon_06,
			                    R.drawable.icon_07, R.drawable.icon_08,
			                    R.drawable.icon_09}; 
	private int[] IconXArray = { R.drawable.icon_01_hover, R.drawable.icon_02_hover, 
						         R.drawable.icon_03_hover, R.drawable.icon_04_hover,
						         R.drawable.icon_05_hover, R.drawable.icon_06_hover,
						         R.drawable.icon_07_hover, R.drawable.icon_08_hover,
						         R.drawable.icon_09_hover};
	private String[] actionBarTitle = { "貼心", "照護", "醫療" };
	private HashMap<Integer, int[]> typeList = new HashMap<Integer, int[]>();
	
	private int landmarkType = 0;
	private int fragmentTypeFlag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_care);
		activity = FragmentActivityMain.this;
		context = getApplicationContext();
		landmarkType = getIntent().getIntExtra("landmarkType", 0);
		typeList.put(1, new int[] {1, 2});
		typeList.put(2, new int[] {3, 4, 5});
		typeList.put(3, new int[] {6, 7, 8, 9});
		
		//自訂ActionBar的設定。
		
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_custom);
		
		//定義初始化的View資料
		controller = new FragmentActivityMainController(
				FragmentActivityMain.this, getApplicationContext());
		
		llOptionBtn = (LinearLayout) findViewById(R.id.llOptionBtn);
		ETSearch = (EditText) findViewById(R.id.ETSearch);
		BtnMap = (Button)findViewById(R.id.BTNMap);
		//標題設定與顯示。
		TextView tvTitle = (TextView)findViewById(R.id.CustomActionBarTitle);
		tvTitle.setText(actionBarTitle[landmarkType - 1]);
		
		//加入選項鈕
		addOptionBtn(landmarkType);
		
		// 從資料庫取得地標資料。
		RequestOptionList(ETSearch.getText().toString());
		
		//初始化View的功能，以及顯示。
		BtnMap.setVisibility(View.VISIBLE);
		
		
		//ActionBar上的地圖按鈕的點擊動作。
		Fragment fragment0 = new FragmentLandmarkList();
		currentFragment = (InterfaceUpdateFragment) fragment0;
		getFragmentManager().beginTransaction().replace(R.id.contentFrame, fragment0).commit();
		BtnMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(fragmentTypeFlag == 0){
					Fragment fragment1 = new FragmentMap();
					currentFragment = (InterfaceUpdateFragment) fragment1;
					getFragmentManager().beginTransaction().replace(R.id.contentFrame, fragment1).commit();
					((Button)v).setText("清單");
					fragmentTypeFlag = 1;
					RequestOptionList(ETSearch.getText().toString());
					
				}
				else{
					Fragment fragment0 = new FragmentLandmarkList();
					currentFragment = (InterfaceUpdateFragment) fragment0;
					getFragmentManager().beginTransaction().replace(R.id.contentFrame, fragment0).commit();
					((Button)v).setText("地圖");
					fragmentTypeFlag = 0;
					RequestOptionList(ETSearch.getText().toString());
				}	
			}
		});
		
		//點擊清除後，清除輸入框。
		Button btnDelete = (Button)findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ETSearch.setText("");
			}
		});

		//當搜尋框中的文字變更後，則做搜尋動作。
		ETSearch.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				v.setFocusable(true);
				v.setFocusableInTouchMode(true);
				return false;
			}
		});
		
		ETSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				RequestOptionList(s.toString());
				//controller.ExecSearchLandmarkListDataTask(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//啟動GPS
		controller.startGPS();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//停止GPS
		controller.stopGPS();
	}

	//當選項按鈕點擊時，則搜索適當的分類，顯示在清單上。
	public void RequestOptionList(String searchStr) {
		StringBuilder OptionStr = new StringBuilder("0");
		String[] ary = new String[]{"4","5","6","7","8","9","10","11","12"};
		
		for(int i=0;i<ary.length;i++){
			if(IsShowArray[i]){
				OptionStr.append(", ").append(ary[i]);
			}
		}
			
		String OptionStr1 = OptionStr.substring(0);
		controller.ExecOptionLandmarkListDataTask(OptionStr1, searchStr);
	}
	
	//更新清單的Function
	@Override
	public void afterGetLandmarkDataList(ArrayList<LandmarkDBEntity> datalist) {
		// TODO Auto-generated method stub
		currentFragment.UpdateFragment(datalist);
	}
	
	public void addOptionBtn(int landmarkType){
		int[] targetList = typeList.get(landmarkType);
		for(int i = 0; i < targetList.length; i++){
			IsShowArray[targetList[i] - 1] = true;
			AddButtonTollOption(targetList[i] - 1);
		}
	}
	
	public void AddButtonTollOption(final int btnSeq){
		int margine5 = (int)CommonFunction.pxFromDp(context, 5);
		int margine10 = (int)CommonFunction.pxFromDp(context, 10);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(margine10, margine5, margine10, margine5);
		
		Button btn = new Button(context);
		btn.setBackgroundResource(IconArray[btnSeq]);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(IsShowArray[btnSeq]){
					IsShowArray[btnSeq] = false;
					v.setBackgroundResource(IconXArray[btnSeq]);
				}
				else{
					IsShowArray[btnSeq] = true;
					v.setBackgroundResource(IconArray[btnSeq]);
				}
				RequestOptionList(ETSearch.getText().toString());
			}
		});
		llOptionBtn.addView(btn, params);
	}

}

package tw.gov.tainan.tainanwelfare.adapter;

import java.util.ArrayList;
import java.util.TreeMap;

import com.google.android.gms.internal.df;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.DialogFragmentLandmarkDetail;
import tw.gov.tainan.tainanwelfare.comm.CommonFunction;
import tw.gov.tainan.tainanwelfare.comm.Util;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//此class為LandmarkItem發送器
public class LandmarkItemAdapter extends ArrayAdapter<LandmarkDBEntity>{

	private Context context;
	private Activity activity;
	private ArrayList<LandmarkDBEntity> datalist;
	private TreeMap<Double, LandmarkDBEntity> sortedmap = new TreeMap<Double, LandmarkDBEntity>();
	private Object[] allkey;
	private int[] listIconArray = { R.drawable.lefticon_01, R.drawable.lefticon_02, 
									R.drawable.lefticon_03, R.drawable.lefticon_04, 
									R.drawable.lefticon_05, R.drawable.lefticon_06, 
									R.drawable.lefticon_07, R.drawable.lefticon_08, 
									R.drawable.lefticon_09 };
	
	public LandmarkItemAdapter(Activity activity, Context context, ArrayList<LandmarkDBEntity> objects) {
		super(context, R.layout.lvitem_landmarklist, objects);
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.context = context;
		this.datalist = objects;
		InitalTreeMap();
	}
	
	//在此計算地標與目前位置距離，加到TreeMap中，加以排序。
	public void InitalTreeMap(){
		double currentLatitude = Util.currentLatitude;
		double currentLongitude = Util.currentLongitude;
		for(LandmarkDBEntity entity : datalist){
			double key = CommonFunction.distFrom(currentLatitude, currentLongitude, 
					entity.getLatitude(), entity.getLongitude());
			double newValue = key;
			while(sortedmap.containsKey(newValue)){
				newValue = newValue + 0.000000001;
			}		
			sortedmap.put(newValue, entity);
		}
		allkey = sortedmap.keySet().toArray();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		LandmarkItemAdapterHolder holder = null;
		String distince = String.valueOf(allkey[position]);
		
		if (convertView != null
				&& (position == ((LandmarkItemAdapterHolder) convertView.getTag()).position)) {
			holder = (LandmarkItemAdapterHolder) convertView.getTag();
		} else {
			LayoutInflater inflater = activity.getLayoutInflater();
			row = inflater.inflate(R.layout.lvitem_landmarklist, null);
			holder = new LandmarkItemAdapterHolder();
			holder.DataEntity = sortedmap.get(Double.parseDouble(distince));
			holder.IVTypeIcon = (ImageView) row.findViewById(R.id.IVTypeIcon);
			holder.TVLandmarkName = (TextView) row.findViewById(R.id.TVLandmarkName);
			holder.TVLandmarkTown = (TextView) row.findViewById(R.id.TVLandmarkTown);
			holder.TVLandmarkDistence = (TextView) row.findViewById(R.id.TVLandmarkDistence);
			holder.position = position;
			row.setTag(holder);
		}
		
		//顯示名稱 區域 ICON
		holder.IVTypeIcon.setBackgroundResource(listIconArray[holder.DataEntity.getMarkTypeSeq() - 4]);
		holder.TVLandmarkName.setText(holder.DataEntity.getName());
		holder.TVLandmarkTown.setText(holder.DataEntity.getTown());
		
		//底下應計算目前位置，算出與目標距離後
		holder.TVLandmarkDistence.setText(String.format("%.2f", Float.parseFloat(distince)) + "公里");
		final LandmarkDBEntity data = holder.DataEntity;
		row.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragmentLandmarkDetail dfDetail = new DialogFragmentLandmarkDetail(data, activity, context, false);
				FragmentManager fragmentManager = activity.getFragmentManager();
				dfDetail.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_No_Border);
				dfDetail.show(fragmentManager, "DetailDialog");
			}
		});
		
		return row;
	}
	
	public class LandmarkItemAdapterHolder{
		public LandmarkDBEntity DataEntity;
		public ImageView IVTypeIcon;
		public TextView TVLandmarkName;
		public TextView TVLandmarkTown;
		public TextView TVLandmarkDistence;
		public int position;
	}
}

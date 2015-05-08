package tw.gov.tainan.tainanwelfare.adapter;

import java.util.ArrayList;
import java.util.List;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.adapter.LandmarkItemAdapter.LandmarkItemAdapterHolder;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import tw.gov.tainan.tainanwelfare.dbentity.NewsDBEntity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsItemAdapter extends ArrayAdapter<NewsDBEntity>{
	
	private ArrayList<NewsDBEntity> datalist;
	private Context context;

	public NewsItemAdapter(Context context, ArrayList<NewsDBEntity> objects) {
		super(context, R.layout.lvitem_news, objects);
		// TODO Auto-generated constructor stub
		this.datalist = objects;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		NewsItemAdapterHolder holder = null;
		
		if (convertView != null
				&& (position == ((NewsItemAdapterHolder) convertView.getTag()).position)) {
			holder = (NewsItemAdapterHolder) convertView.getTag();
		} else {
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(R.layout.lvitem_news, null);
			holder = new NewsItemAdapterHolder();
			holder.DataEntity = datalist.get(position);
			holder.TVNewsTitle = (TextView) row.findViewById(R.id.ndTitle);
			holder.TVNewsTime = (TextView) row.findViewById(R.id.ndTime);
			holder.position = position;
			row.setTag(holder);
		}
		
		holder.TVNewsTime.setText(holder.DataEntity.getRELEASE_TIME().substring(0, 10));
		holder.TVNewsTitle.setText(holder.DataEntity.getTITLE());
		return row;
	}
	
	public class NewsItemAdapterHolder{
		public NewsDBEntity DataEntity;
		public TextView TVNewsTitle;
		public TextView TVNewsTime;
		public int position;
	}
}

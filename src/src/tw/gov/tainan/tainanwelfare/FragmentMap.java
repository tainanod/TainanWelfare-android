package tw.gov.tainan.tainanwelfare;

import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.adapter.LandmarkItemAdapter;
import tw.gov.tainan.tainanwelfare.comm.CommonFunction;
import tw.gov.tainan.tainanwelfare.comm.InterfaceUpdateFragment;
import tw.gov.tainan.tainanwelfare.comm.Util;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentMap extends MapFragment implements InterfaceUpdateFragment{
	
	private GoogleMap map;
	private MapFragment mapFragment;
	private FragmentActivityMain activity;
	private Context context; 
	
	private boolean inTainan; 
	
	public FragmentMap(){
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.activity = (FragmentActivityMain) activity;
		this.context = activity.getApplicationContext();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if(map == null){
			map = this.getMap();
        	map.setMyLocationEnabled(true);
         	CameraUpdate center = null;
         	boolean inTainan = (Util.currentLatitude > Util.tainanAreaLeft && Util.currentLatitude < Util.tainanAreaRight) &&
					(Util.currentLongitude > Util.tainanAreaButtom && Util.currentLatitude < Util.tainanAreaTop);
			if(inTainan){
				center = CameraUpdateFactory.newLatLng(
	         			new LatLng(Util.currentLatitude, Util.currentLongitude));
			}
			else{
				center = CameraUpdateFactory.newLatLng(
	         			new LatLng(Util.tainanTransationLat, Util.tainanTransationLng));
			}
			
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);
            map.moveCamera(center);
            map.animateCamera(zoom);
        }
	}
	
	@Override
	public void UpdateFragment(final ArrayList<LandmarkDBEntity> UpdateData) {
		// TODO Auto-generated method stub
		map.clear();
		for(LandmarkDBEntity data : UpdateData){
			Marker marker = map.addMarker(new MarkerOptions()
	        .position(new LatLng(data.getLatitude(), data.getLongitude()))
	        .title(data.getName())
	        .icon(BitmapDescriptorFactory.fromResource(Util.markerIconList[data.getMarkTypeSeq() - 4])));
		}
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				LandmarkDBEntity targetdata = new LandmarkDBEntity();
				for(LandmarkDBEntity data : UpdateData){
					if(data.getName().equalsIgnoreCase(marker.getTitle())){
						targetdata = data;
					}
				}
				DialogFragmentLandmarkDetail dfDetail = 
						new DialogFragmentLandmarkDetail(targetdata, activity, context, true);
				FragmentManager fragmentManager = getFragmentManager();
				dfDetail.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_No_Border);
				dfDetail.show(fragmentManager, "DetailDialog");
				return false;
			}
		});
	}

}

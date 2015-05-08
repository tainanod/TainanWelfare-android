package tw.gov.tainan.tainanwelfare;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.comm.Util;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragemtActivityLandmarkMap extends Activity{

	private GoogleMap map;
	private LandmarkDBEntity data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentactivity_landmarkmap);
		
		this.data = (LandmarkDBEntity)getIntent().getExtras().get("data");
		
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setCustomView(R.layout.actionbar_custom);
		TextView tvTitle = (TextView)findViewById(R.id.CustomActionBarTitle);
		tvTitle.setText("照護");
		
		//GoogleMap顯示初始化
		if(map == null){
        	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        	 if(map != null){
        		 Marker m1 = map.addMarker(new MarkerOptions()
                 .position(new LatLng(data.getLatitude(), data.getLongitude()))
                 .title(data.getName())
                 .icon(BitmapDescriptorFactory.fromResource(Util.markerIconList[data.getMarkTypeSeq() - 4])));
        		m1.showInfoWindow();
             	map.setMyLocationEnabled(true);
             	CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(data.getLatitude(), data.getLongitude()));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                map.moveCamera(center);
                map.animateCamera(zoom);
             }
        }
	}
	
	
}

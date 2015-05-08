package tw.gov.tainan.tainanwelfare;

import java.util.ArrayList;

import tw.gov.tainan.tainanwelfare.R;
import tw.gov.tainan.tainanwelfare.adapter.LandmarkItemAdapter;
import tw.gov.tainan.tainanwelfare.comm.InterfaceUpdateFragment;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentLandmarkList extends Fragment implements InterfaceUpdateFragment{

	private FragmentActivityMain activity;
	private Context context;
	private ListView LVlandmark;
	private LandmarkItemAdapter LVlandmarkAdapter; 
	
	public FragmentLandmarkList(){
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.activity = (FragmentActivityMain) getActivity();
		this.context = getActivity().getApplicationContext();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_landmark, container, false);
		LVlandmark = (ListView) rootView.findViewById(R.id.LVlandmark);
		return rootView;
	}

	@Override
	public void UpdateFragment(ArrayList<LandmarkDBEntity> UpdateData) {
		// TODO Auto-generated method stub
		LVlandmarkAdapter = new LandmarkItemAdapter(activity, context, UpdateData);
		LVlandmark.setAdapter(LVlandmarkAdapter);
		LVlandmarkAdapter.notifyDataSetChanged();
	}
	
}

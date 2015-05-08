package tw.gov.tainan.tainanwelfare.comm;

import java.util.ArrayList;

import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;

public interface InterfaceGetLandmark {
	public void afterGetLandmarkDataList(ArrayList<LandmarkDBEntity> datalist);
}

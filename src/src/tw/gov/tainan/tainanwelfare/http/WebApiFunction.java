package tw.gov.tainan.tainanwelfare.http;

import java.util.ArrayList;
import org.json.JSONException;
import tw.gov.tainan.tainanwelfare.comm.Util;
import tw.gov.tainan.tainanwelfare.dbentity.CategoryDBEntity;
import tw.gov.tainan.tainanwelfare.dbentity.LandmarkDBEntity;
import tw.gov.tainan.tainanwelfare.dbentity.NewsDBEntity;
import tw.gov.tainan.tainanwelfare.dbentity.ODJsonParser;

public class WebApiFunction {
	
	HttpController service_reply = new HttpController();

	public ArrayList<CategoryDBEntity> GetAllCategory() throws JSONException{
		String JsonResult = service_reply.RequestWebApiGet(Util.WebServiceUrl + "Category"); 
		ODJsonParser parser = new ODJsonParser();
		return parser.ParseToCategoryList(JsonResult);
	} 
	
	public ArrayList<LandmarkDBEntity> GetCategoryLandmark(int CategorySeq) throws JSONException{
		String jsonResultString = service_reply.RequestWebApiGet(Util.WebServiceUrl + "Poi/" + CategorySeq);
		ODJsonParser parser = new ODJsonParser();
		return parser.ParseToLandmarkList(jsonResultString);
	}
	
	public ArrayList<NewsDBEntity> GetNewsList() throws JSONException{
		String jsonResultString = service_reply.RequestWebApiGet(Util.WebServiceUrl + "rssnews?$top=10" );
		return ODJsonParser.ParseToNewsList(jsonResultString);
	}
	
}

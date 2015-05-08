package tw.gov.tainan.tainanwelfare.db;

import java.util.ArrayList;

import tw.gov.tainan.tainanwelfare.dbentity.CategoryDBEntity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class category_PO {
	
	private Context context;
	private DBHelper dbhelper;

	public category_PO(Context context) {
		this.context = context;
		dbhelper = DBHelper.getInstance(context); 
	}

	public ArrayList<CategoryDBEntity> GetAllCategory(){
		ArrayList<CategoryDBEntity> result = new ArrayList<CategoryDBEntity>();
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor c1 = db.rawQuery("SELECT SEQ, CATEGORY_NAME, UPPER_SEQ, VERSION FROM CATEGORY", null);
		if(c1.getCount() > 0){
			c1.moveToFirst();
			while(!c1.isAfterLast()){
				CategoryDBEntity object = new CategoryDBEntity();
				object.setSeq(c1.getInt(0));
				object.setCategoryName(c1.getString(1));
				object.setUpperSeq(c1.getInt(2));
				object.setVersion(c1.getInt(3));
				result.add(object);
				c1.moveToNext();
			}
		}
		return result;
	}
	
	public CategoryDBEntity GetCategory(int Seq){
		ArrayList<CategoryDBEntity> result = new ArrayList<CategoryDBEntity>();
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor c1 = db.rawQuery("SELECT SEQ, CATEGORY_NAME, UPPER_SEQ, VERSION FROM CATEGORY WHERE SEQ = ?"
				, new String[] { String.valueOf(Seq) });
		if(c1.getCount() > 0){
			c1.moveToFirst();
			CategoryDBEntity object = new CategoryDBEntity();
			object.setSeq(c1.getInt(0));
			object.setCategoryName(c1.getString(1));
			object.setUpperSeq(c1.getInt(2));
			object.setVersion(c1.getInt(3));
			return object;
		}
		else{
			return null;
		}
	}
	
	public void InsertCategory(CategoryDBEntity object){
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("SEQ", object.getSeq());
		values.put("CATEGORY_NAME", object.getCategoryName());
		values.put("UPPER_SEQ", object.getUpperSeq());
		values.put("VERSION", object.getVersion());
		db.replace("CATEGORY", null, values);
	}
	
}

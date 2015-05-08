package tw.gov.tainan.tainanwelfare.dbentity;

public class CategoryDBEntity {
	
	private int Seq;
	private String CategoryName;
	private int UpperSeq;
	private int Version;
	
	public int getSeq() {
		return Seq;
	}
	public void setSeq(int seq) {
		Seq = seq;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public int getUpperSeq() {
		return UpperSeq;
	}
	public void setUpperSeq(int upperSeq) {
		UpperSeq = upperSeq;
	}
	public int getVersion() {
		return Version;
	}
	public void setVersion(int version) {
		Version = version;
	}

}

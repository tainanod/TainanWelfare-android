package tw.gov.tainan.tainanwelfare.dbentity;

public class NewsDBEntity {
	
	private String SEQ;
	private String TITLE;
	private String CONTENT;
	private String RELEASE_TIME;
	private String EDIT_TIME;
	private String CREATOR;
	private String LINK;
	
	public String getSEQ() {
		return SEQ;
	}
	public void setSEQ(String sEQ) {
		SEQ = sEQ;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getRELEASE_TIME() {
		return RELEASE_TIME;
	}
	public void setRELEASE_TIME(String rELEASE_TIME) {
		RELEASE_TIME = rELEASE_TIME;
	}
	public String getEDIT_TIME() {
		return EDIT_TIME;
	}
	public void setEDIT_TIME(String eDIT_TIME) {
		EDIT_TIME = eDIT_TIME;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	public String getLINK() {
		return LINK;
	}
	public void setLINK(String lINK) {
		LINK = lINK;
	}
	
}

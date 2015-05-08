package tw.gov.tainan.tainanwelfare.dbentity;

import java.io.Serializable;
import java.util.HashMap;

public class LandmarkDBEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1736972072988834592L;
	private String SEQ;
	private String name;
	private String town;
	private String address;
	private String phone;
	private float longitude;
	private float latitude;
	private String score;
	private HashMap<String, String> info;
	private int markTypeSeq;
	private float distence;
	
	public String getSEQ() {
		return SEQ;
	}
	public void setSEQ(String sEQ) {
		SEQ = sEQ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public HashMap<String, String> getInfo() {
		return info;
	}
	public void setInfo(HashMap<String, String> info) {
		this.info = info;
	}
	public int getMarkTypeSeq() {
		return markTypeSeq;
	}
	public void setMarkTypeSeq(int markTypeSeq) {
		this.markTypeSeq = markTypeSeq;
	}
	public float getDistence() {
		return distence;
	}
	public void setDistence(float distence) {
		this.distence = distence;
	}
}

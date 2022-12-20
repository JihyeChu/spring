package com.tjoeun.springDI5_xml_namespace;

public class Family {

	private String fatherName;
	private String mamName;
	private String sisterName;
	private String brotherName;

	public Family() {
	}

	public Family(String fatherName, String mamName) {
		this.fatherName = fatherName;
		this.mamName = mamName;
	}

	public String getfatherName() {
		return fatherName;
	}

	public void setfatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMamName() {
		return mamName;
	}

	public void setMamName(String mamName) {
		this.mamName = mamName;
	}

	public String getSisterName() {
		return sisterName;
	}

	public void setSisterName(String sisterName) {
		this.sisterName = sisterName;
	}

	public String getBrotherName() {
		return brotherName;
	}

	public void setBrotherName(String brotherName) {
		this.brotherName = brotherName;
	}

	@Override
	public String toString() {
		return "Family [fatherName=" + fatherName + ", mamName=" + mamName + ", sisterName=" + sisterName + ", brotherName="
				+ brotherName + "]";
	}

}

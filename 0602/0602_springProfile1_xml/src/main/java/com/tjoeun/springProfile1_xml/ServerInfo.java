package com.tjoeun.springProfile1_xml;

public class ServerInfo {

	private String ipNumber;
	private String portNumber;
	

	public String getIpNumber() {
		return ipNumber;
	}


	public void setIpNumber(String ipNumber) {
		this.ipNumber = ipNumber;
	}


	public String getPortNumber() {
		return portNumber;
	}


	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}


	@Override
	public String toString() {
		return "ServerInfo [ipNumber=" + ipNumber + ", portNumber=" + portNumber + "]";
	}
	
}

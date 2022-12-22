package com.tjoeun.springProperties1_Environment;

//	AdminConnection 클래스의 bean이 생성되는 순간 admin.properties 파일의 내용을 읽어서 필드에 저장한다.
public class AdminConnection {

	private String adminId; // admin.properties 파일의 admin.id에 할당된 데이터를 저장한다.
	private String adminPw; // admin.properties 파일의 admin.pw에 할당된 데이터를 저장한다.
	
	public AdminConnection() {
		System.out.println("AdminConnection 클래스의 bean이 생성되었습니다.");
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	@Override
	public String toString() {
		return "AdminConnection [adminId=" + adminId + ", adminPw=" + adminPw + "]";
	}
	
	
	
}

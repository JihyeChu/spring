package com.tjoeun.springAOP2_java;

public class Worker {

	private String name;
	private int age;
	private String job;
	
	public Worker() {
		// TODO Auto-generated constructor stub
	}

	public Worker(String name, int age, String job) {
		this.name = name;
		this.age = age;
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Woker [name=" + name + ", age=" + age + ", job=" + job + "]";
	}
	
//	Worker 클래스의 핵심 기능
	public void getWorkerInfo() {
		System.out.println("Worker 클래스의 핵심 기능");
		System.out.println("이름: " + name);
		System.out.println("나이: " + age);
		System.out.println("직업: " + job);
	}
}


























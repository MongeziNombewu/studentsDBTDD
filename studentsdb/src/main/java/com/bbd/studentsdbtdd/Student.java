package com.bbd.studentsdbtdd;

public class Student {

	private String mName;
	private int mAge;
	private String mCity;
	
	public Student(String name, int age, String city) {
		this.setAge(age);
		this.setCity(city);
		this.setName(name);
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public int getAge() {
		return mAge;
	}

	public void setAge(int mAge) {
		this.mAge = mAge;
	}

	public String getCity() {
		return mCity;
	}

	public void setCity(String mCity) {
		this.mCity = mCity;
	}
	
	
}

package com.bbd.studentsdbtdd;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentList {

	private ArrayList<Student> mStudents = null;
	private Connection mConnection = null;
	private IStudentDAO mStudentDAO;
	
	public StudentList(IStudentDAO dao) {
		this.mStudentDAO = dao;
		mStudents = new ArrayList<Student>();
	}
	
	public StudentList() throws SQLException {
		this.mConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/c:\\temp\\db;");
		mStudents = new ArrayList<Student>();
		this.mStudentDAO = new StudentDAO(this.mConnection);
	}
	
	public ArrayList<Student> getStudents() {
		mStudents = this.mStudentDAO.getStudents();
					
		return mStudents;
	}
	
	public boolean clearStudents() {
		boolean success = this.mStudentDAO.clearStudents();
		
		return success;
	}
	
	public boolean addStudent(Student student) {
		boolean id = this.mStudentDAO.addStudent(student);
		this.mStudents.add(student);
		
		return id;
	}
	
	public static void main(String[] args) {				
		try {
			
			StudentList students = new StudentList();		
			
			students.clearStudents();
			
			Student stud = new Student(1, "Tim", 20, "City");
			System.out.println(stud);
			students.addStudent(stud);
			
			System.out.println(students.getStudents());
			
		} catch (SQLException e) {		
			e.printStackTrace();
		} 							

	}

	public IStudentDAO getStudentDAO() {
		return mStudentDAO;
	}

	public void setStudentDAO(IStudentDAO mStudentDAO) {
		this.mStudentDAO = mStudentDAO;
	}

}

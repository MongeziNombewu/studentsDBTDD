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
	
	private final String SQL_GET_STUDENTS = "SELECT * FROM students;";
	private final String SQL_DELETE_STUDENTS = "TRUNCATE TABLE students;";
	private final String SQL_ADD_STUDENT = "INSERT INTO students (NAME, AGE, CITY) VALUES (?, ?, ?);";
	
	public StudentList(Connection conn) {
		this.mConnection = conn;
	}
	
	public ArrayList<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		
		try {
			Statement statement = mConnection.createStatement();
			ResultSet rs = statement.executeQuery(SQL_GET_STUDENTS);
			
			while (rs.next()) {
				int age = rs.getInt(2);
				String name = rs.getString(1);
				String city = rs.getString(3);
				
				students.add(new Student(
						name,
						age,
						city
						));				
			}
			
			this.mStudents.addAll(students);
		} catch (SQLException ex) {
			ex.getMessage();
		} finally {			
            try {
            	mConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
		
		return students;
	}
	
	public boolean clearStudents() {
		boolean success = false;
		
		try {			
			PreparedStatement statement = mConnection.prepareStatement(SQL_DELETE_STUDENTS);
			statement.executeUpdate();
			
			this.mStudents = null;
			
			success = true;
		} catch (SQLException ex) {
			ex.getMessage();
		} finally {			
            try {
            	mConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
		
		return success;
	}
	
	public int addStudent(Student student) {
		int id = 0;
		
		try {						
			PreparedStatement statement = mConnection.prepareStatement(SQL_ADD_STUDENT);
			
			statement.setString(1, student.getName());
			statement.setInt(2, student.getAge());
			statement.setString(3, student.getCity());
								
			id = statement.executeUpdate();	
			
			this.mStudents.add(student);
		} catch (SQLException ex) {
			ex.getMessage();
		} finally {			
            try {
            	mConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
		
		return id;
	}
	
	public static void main(String[] args) {
		Connection conn;
		
		try {
			conn = DriverManager.getConnection("jdbc:derby://localhost:1527/c:\\temp\\db;");
			
			StudentList students = new StudentList(conn);
			Student stud = new Student("Tim", 20, "City");
			
			students.addStudent(stud);
			
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		
		
		
		

	}

}

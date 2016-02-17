package com.bbd.studentsdbtdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO implements IStudentDAO {
	
	private Connection mConnection = null;
	
	private final String SQL_GET_STUDENTS = "SELECT * FROM students";
	private final String SQL_DELETE_STUDENTS = "TRUNCATE TABLE students";
	private final String SQL_ADD_STUDENT = "INSERT INTO students (ID, NAME, AGE, CITY) VALUES (?, ?, ?, ?)";
	
	public StudentDAO(Connection conn) {
		this.mConnection = conn;		
	}
	
	public ArrayList<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		
		try {
			Statement statement = mConnection.createStatement();
			ResultSet rs = statement.executeQuery(SQL_GET_STUDENTS);
			
			while (rs.next()) {
				int age = rs.getInt(3);
				String name = rs.getString(2);
				String city = rs.getString(4);
				int id = rs.getInt(1);
				
				students.add(new Student(
						id,
						name,
						age,
						city
						));				
			}					
		} catch (SQLException ex) {
			ex.getMessage();
		} finally {			
            /*try {
            	mConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
		}
		
		return students;
	}

	public boolean addStudent(Student student) {
		boolean success = false;
		
		try {					
			PreparedStatement statement = mConnection.prepareStatement(SQL_ADD_STUDENT);
			
			statement.setInt(1, student.getID());
			statement.setString(2, student.getName());
			statement.setInt(3, student.getAge());
			statement.setString(4, student.getCity());
				
			statement.executeUpdate();
			
			success = true;	
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {			
            /*try {
            	mConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
		}
		
		return success;
	}

	public boolean clearStudents() {
		boolean success = false;
		
		try {			
			PreparedStatement statement = mConnection.prepareStatement(SQL_DELETE_STUDENTS);
			statement.executeUpdate();			
			
			success = true;
		} catch (SQLException ex) {
			ex.getMessage();
		} finally {			
            /*try {
            	mConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
		}
		
		return success;
	}

}

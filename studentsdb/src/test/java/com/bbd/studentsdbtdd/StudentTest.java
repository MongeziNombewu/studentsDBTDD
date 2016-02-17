package com.bbd.studentsdbtdd;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class StudentTest {
	Student student1;
	Student student2;
	Student student3;
	StudentList studentList;
	
	//setup the StudentList
	//setup the 3 Student objects - getName, getAge, getCity
	@Before
	public void setUp() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/c://temp//db");
		studentList = new StudentList(conn);
		student1 = new Student("Amy", 18, "Johannesburg");
		student2 = new Student("Mongezi", 24, "Johannesburg");
		student3 = new Student("Rory", 28, "Johannesburg");
		
	}

	//test 1 - cleans StudentList - clearStudents()
	//test 2 - inserts StudentList - insertStudent(Student)
	//test 3 - retrieves ArrayList<Student> from StudentList - getStudents()
	
	@Test
	public void testClean() {
		assertEquals(true, studentList.clearStudents());
	}
	
	@Test
	public void testInsert(){
		assertEquals(true, studentList.insertStudent(student1));
		assertEquals(true, studentList.insertStudent(student2));
		assertEquals(true, studentList.insertStudent(student3));
	}
	
	@Test
	public void testRetrieve(){
		ArrayList<Student> students = studentList.getStudents();
		Student student1 = students.get(0);
		assertEquals("Amy", student1.getName());
		assertEquals(18, student1.getAge());
		assertEquals("Johannesburg", student1.getCity());
		Student student2 = students.get(1);
		assertEquals("Mongezi", student2.getName());
		assertEquals(24, student2.getAge());
		assertEquals("Johannesburg", student2.getCity());
		Student student3 = students.get(2);
		assertEquals("Rory", student3.getName());
		assertEquals(28, student3.getAge());
		assertEquals("Johannesburg", student3.getCity());
	}

}

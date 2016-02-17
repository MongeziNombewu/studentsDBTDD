package com.bbd.studentsdbtdd;

import java.util.ArrayList;

public interface IStudentDAO {

	ArrayList<Student> getStudents();
	boolean addStudent(Student student);
	boolean clearStudents();
}

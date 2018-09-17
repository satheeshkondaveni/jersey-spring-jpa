package com.ksbs.rest.dao;

import java.util.List;

import com.ksbs.rest.entity.Student;
import com.ksbs.rest.init.modal.StudentModal;

public interface StudentDAO {
	public List<Student> getAllStudents();
	public Student getStudent(int id);
	public Student addStudent(Student student);
	public void updateStudent(StudentModal student);
	public boolean deleteStudent(int id);
}

package com.ksbs.rest.services;

import java.util.List;

import com.ksbs.rest.entity.Student;
import com.ksbs.rest.init.modal.StudentModal;

public interface StudentService {
	public List<Student> getAllStudents();
	public Student getStudent(int id);
	public Student addStudent(StudentModal student);
	public void updateStudent(StudentModal student);
	public boolean deleteStudent(int id);
}

package com.ksbs.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ksbs.rest.dao.StudentDAO;
import com.ksbs.rest.entity.Student;
import com.ksbs.rest.init.modal.StudentModal;

@Service("studentService")
@Transactional(propagation=Propagation.REQUIRED)
public class StudentServiceImpl implements StudentService {
	@Autowired(required=true)
	StudentDAO stuentDao;
	
	
	public List<Student> getAllStudents() {
		return stuentDao.getAllStudents();
	}


	
	public Student getStudent(int id) {
		// TODO Auto-generated method stub
		return stuentDao.getStudent( id);
	}


	
	public Student addStudent(StudentModal student) {
		// TODO Auto-generated method stub
		Student st=new Student();
		st.setStudentName(student.getStudentName());
		st.setBranch(student.getBranch());
		st.setMarks(student.getMarks());
		
		return stuentDao.addStudent( st);
	}

	
	public void updateStudent(StudentModal student) {
		// TODO Auto-generated method stub
		
		stuentDao.updateStudent( student);
	}

	
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		return stuentDao.deleteStudent(id);
	}
}

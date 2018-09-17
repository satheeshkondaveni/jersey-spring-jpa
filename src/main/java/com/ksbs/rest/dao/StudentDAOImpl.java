package com.ksbs.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ksbs.rest.entity.Student;
import com.ksbs.rest.init.modal.StudentModal;

@Service("stuentDao")
@Transactional(propagation=Propagation.REQUIRED)
public class StudentDAOImpl implements StudentDAO {

	@PersistenceContext
	public EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Student> getAllStudents() {
		 List<Student> studentList = entityManager.createQuery("SELECT s from Student s").getResultList();
		 return studentList;
	}

	@Transactional(readOnly=true)
	public Student getStudent(int id) {
		 Query query  = entityManager.createQuery("select student from Student student where student.roll_no=:roll_no");
		 query.setParameter("roll_no", id);
		 return (Student)query.getSingleResult();
	}
	@Transactional(readOnly=false)
	public Student addStudent(Student student) {
		entityManager.persist(student);
		return student;
	}

	@Transactional(readOnly=true)
	public void updateStudent(StudentModal student) {
		// TODO Auto-generated method stub
		Student st=getStudent(student.getRoll_no());
		st.setStudentName(student.getStudentName());
		st.setBranch(student.getBranch());
		st.setMarks(student.getMarks());
		entityManager.flush();
	}

	@Transactional(readOnly=true)
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		boolean flag=false;
if(getStudent(id)!=null) {
		entityManager.remove(getStudent(id));
		flag=true;
}else {
	flag =false;
}
		return flag;
	}

}

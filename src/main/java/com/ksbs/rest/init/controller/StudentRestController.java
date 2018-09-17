package com.ksbs.rest.init.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ksbs.rest.entity.Student;
import com.ksbs.rest.init.modal.StudentModal;
import com.ksbs.rest.init.modal.StudentStatusModal;
import com.ksbs.rest.services.StudentService;

@Component
@Path("/student")
public class StudentRestController {

	
	@Autowired(required=true)
	@Qualifier("studentService")
	private StudentService studentService;
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/student-json-meta")
	public StudentStatusModal getStudent(){
		StudentStatusModal studentStatus = new StudentStatusModal();
		StudentModal studentModal = new StudentModal();
		studentModal.setStudentName("Test from Service");
		studentModal.setBranch("CSE");
		studentModal.setMarks(100);
		studentModal.setRoll_no(55);
		studentStatus.setStatus(200);
		studentStatus.setMessage("Student info");
		studentStatus.setStudent(studentModal);
		return studentStatus;
	}
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllStudents")
	public StudentStatusModal getAllStudents(){
		StudentStatusModal studentStatus = new StudentStatusModal();
		//StudentModal studentModal = new StudentModal();
		
	List<Student> studentList=	studentService.getAllStudents();
		
		
		studentStatus.setStatus(200);
		studentStatus.setMessage("Student info");
		studentStatus.setStudentList(studentList);
		return studentStatus;
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/student-info/{studentId}")
	public StudentStatusModal getStudent(@PathParam("studentId") int studentId){
		
		System.out.println("student-info:studentId"+studentId);
		
		StudentStatusModal studentStatus = new StudentStatusModal();
		Student student = studentService.getStudent(studentId);
		
		StudentModal studentModal = new StudentModal();
		if(student!=null){
			studentStatus.setStatus(200);
			studentStatus.setMessage("Student info");
			studentModal.setStudentName(student.getStudentName());
			studentModal.setBranch(student.getBranch());
			studentModal.setMarks(student.getMarks());
			studentModal.setRoll_no(student.getRoll_no());
			studentStatus.setStudent(studentModal);
		}else{
			studentStatus.setStatus(205);
			studentStatus.setMessage("Student info");
		}
		return studentStatus;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create-student")
	public StudentStatusModal createStudent(StudentModal studentModal){
		System.out.println("create Student:"+studentModal.getStudentName());
		Student student = null;
		StudentStatusModal status = new StudentStatusModal();
		
		try{
			
			 student = studentService.addStudent(studentModal);
			 	studentModal.setStudentName(student.getStudentName());
				studentModal.setBranch(student.getBranch());
				studentModal.setMarks(student.getMarks());
				status.setStudent(studentModal);
				status.setStatus(201);
				status.setMessage("Student Created Successfully");
			
		}catch(Exception e){
			status.setStatus(205);
			 status.setMessage("Error in Creating students:"+e.getMessage());
		}
		
		
		
		return status;
		
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateStudent")
	public StudentStatusModal updateStudent(StudentModal studentModal){
		System.out.println("updateStudent :"+studentModal.getStudentName());
		Student student = null;
		StudentStatusModal studentStatusModal = new StudentStatusModal();
		
		try{
			
			    studentService.updateStudent(studentModal);
			     student = studentService.getStudent(studentModal.getRoll_no());
				 studentModal = new StudentModal();
				if(student!=null){
					studentStatusModal.setStatus(200);
					studentStatusModal.setMessage("Student Deatails Updated Successfully.");
					studentModal.setStudentName(student.getStudentName());
					studentModal.setBranch(student.getBranch());
					studentModal.setMarks(student.getMarks());
					studentStatusModal.setStudent(studentModal);
				}else{
					studentStatusModal.setStatus(205);
					studentStatusModal.setMessage("Failed to update student deatials ");
				}
			
		}catch(Exception e){
			studentStatusModal.setStatus(205);
			studentStatusModal.setMessage("Error in update student deatials :"+e.getMessage());
		}
		
		return studentStatusModal;
		
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteStudent/{studentId}")
	public StudentStatusModal deleteStudent(@PathParam("studentId") int studentId){
		
		System.out.println("deleteStudent:studentId"+studentId);
		
		StudentStatusModal studentStatusModal = new StudentStatusModal();
		
		try {
		if( studentService.deleteStudent(studentId)) {
		 studentStatusModal.setStatus(200);
		 studentStatusModal.setMessage("Student Deatails Deleted Successfully.");
		}else {
			 studentStatusModal.setStatus(204);
			 studentStatusModal.setMessage("NO CONTENT");
		}
		}catch(Exception e){
			studentStatusModal.setStatus(205);
			studentStatusModal.setMessage("Error in update student deatials :"+e.getMessage());
		}
		return studentStatusModal;
	}
}

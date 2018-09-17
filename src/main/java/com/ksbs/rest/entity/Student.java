package com.ksbs.rest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="student")
@Entity
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2470336780149787178L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roll_no;
	
	private String studentName;
	
	private String branch;
	
	private int marks;


	public int getRoll_no() {
		return roll_no;
	}


	public void setRoll_no(int roll_no) {
		this.roll_no = roll_no;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getBranch() {
		return branch;
	}


	public void setBranch(String branch) {
		this.branch = branch;
	}


	public int getMarks() {
		return marks;
	}


	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	
}

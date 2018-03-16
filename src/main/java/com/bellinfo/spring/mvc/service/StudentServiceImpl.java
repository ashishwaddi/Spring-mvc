package com.bellinfo.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bellinfo.spring.mvc.model.Student;
import com.bellinfo.spring.mvc.repository.StudentRepository;

@Component
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository repository;

	public void processStudentData(Student student) {

		System.out.println("");
		//repository.persistStudentData(student);
	}


	
	
	

	
	
}

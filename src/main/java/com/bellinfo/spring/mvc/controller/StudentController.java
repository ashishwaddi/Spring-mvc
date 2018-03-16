package com.bellinfo.spring.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bellinfo.spring.mvc.model.Student;
import com.bellinfo.spring.mvc.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@RequestMapping(value="/student",method=RequestMethod.GET)
	public String getStudent(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		return "student-login";
	}
	
	@RequestMapping(value="/student",method=RequestMethod.POST)
	public String getStudent(@Valid @ModelAttribute Student student,BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "student-login";
		}
		String text = "Hey" +student.getName()+ "welcome to spring mvc"+" your id is "+student.getId();
		model.addAttribute("message", text);
		return "success";
		
	}
	
}

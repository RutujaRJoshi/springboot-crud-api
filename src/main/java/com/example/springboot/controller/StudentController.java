package com.example.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Student;
import com.example.springboot.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	
	//get all students
	@GetMapping("students")
	public List<Student> getAllStudents() {
		return this.studentRepository.findAll();
	}
	
	//insert student
	@PostMapping("students")
	public Student createStudent(@RequestBody Student student) {
		return this.studentRepository.save(student);
	}
	
	//update student
	@PutMapping("/students/{rollNo}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value="rollNo") int rollNo, 
			@Validated @RequestBody Student studentDetails) {
		Student student = studentRepository.getReferenceById(rollNo);
		student.setFirstName(studentDetails.getFirstName());
		student.setLastName(studentDetails.getLastName());
		student.setStd(studentDetails.getStd());
		student.setDiv(studentDetails.getDiv());
		
		return ResponseEntity.ok(this.studentRepository.save(student));
	}
	
	//delete employee
	@DeleteMapping("/students/{rollNo}")
	public String deleteStudent(@PathVariable(value="rollNo") int rollNo) {
		
		this.studentRepository.deleteById(rollNo);
		return "Student with roll no " + rollNo + " is deleted";
	}
}

package telran.students.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.students.api.StudentURI;
import telran.students.dto.ScoreDto;
import telran.students.dto.StudentDto;
import telran.students.dto.StudentEditDto;
import telran.students.dto.StudentResponseDto;
import telran.students.entities.Student;
import telran.students.service.IStudentService;

@RestController
public class StudentController {
 @Autowired
	IStudentService studentService;
	
	@PostMapping(StudentURI.STUDENT)
	public boolean addStudent(@RequestBody  StudentDto student)
	{
	  	return studentService.addStudent(student);
	}
	@DeleteMapping(StudentURI.STUDENT)
	public StudentResponseDto removeStudent(@RequestParam int id)
	{
		return studentService.deleteStudent(id);
	}
	@PutMapping(StudentURI.STUDENT+"/{id}")
	public StudentDto editStudent(
			@PathVariable int id,
			@RequestBody StudentEditDto student)
	{
		return studentService.editStudent(id, student);
 	}
	@GetMapping(StudentURI.STUDENT+"/{id}")
	public StudentResponseDto getStudent(
			@PathVariable int id)
	{
		return studentService.getStudent(id);
	}
	@PutMapping(StudentURI.TEACHER+"/{id}")
	public boolean addScore(
		@PathVariable	int id,
		@RequestBody ScoreDto score)
	{
		return studentService.addScore(id, score);
	}
	@GetMapping(StudentURI.STUDENT+"/{name}")
	public List<Student>getByName(@PathVariable String name)
	{
		return studentService.findByNameStartWith(name);
	}
	@GetMapping(StudentURI.Students+"/{name}")
	public List<Student>getByAlefBet(@PathVariable String name)
	{
		return studentService.findByNameAlefBet(name);
	}
	@GetMapping(StudentURI.Students+"/{exam}/{score}")
	List<Student>getBestStudents(String exam,int score)
	
	{
	return studentService.getBestStudents(exam, score);	
	}
}

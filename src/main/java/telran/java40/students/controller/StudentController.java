package telran.java40.students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java40.students.dto.ScoreDto;
import telran.java40.students.dto.StudentDto;
import telran.java40.students.dto.StudentsCredentialsDto;
import telran.java40.students.dto.UpdateStudentDto;
import telran.java40.students.service.StudentService;

@RestController
public class StudentController {
	
	StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/student")
	public boolean studentRegister(@RequestBody StudentsCredentialsDto studentsCredentialsDto) {
		return studentService.addStudent(studentsCredentialsDto);

	}

	@GetMapping("/student/{id}")
	public StudentDto findStudentById(@PathVariable Integer id) {
		return studentService.findStudent(id);
	}

	@DeleteMapping("/student/{id}")
	public StudentDto removeStudent(@PathVariable Integer id) {
		return studentService.deleteStudent(id);
	}

	@PutMapping("/student/{id}")
	public StudentsCredentialsDto editStudent(@PathVariable Integer id, @RequestBody UpdateStudentDto updateStudentDto) {
		return studentService.updateStudent(id, updateStudentDto);
	}
	
	@PutMapping("/score/student/{id}")
	public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
		return studentService.addScore(id, scoreDto);
	}
	
	@GetMapping("/students/name/{name}")
	public List<StudentDto> findStudentsByName(@PathVariable String name) {
		return studentService.findStudentsByName(name);
	}
	
	@PostMapping("/quantity/students")
	public long studentsNamesQuantity(@RequestBody List<String> names) {
		return studentService.getStudentsNamesQuantity(names);
	}
	
	@GetMapping("students/exam/{exam}/minscore/{score}")
	public List<StudentDto> studentsByExamScore(@PathVariable String exam, @PathVariable int score) {
		return studentService.getStudentsByExamScore(exam, score);
	}

}

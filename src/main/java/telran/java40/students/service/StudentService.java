package telran.java40.students.service;

import java.util.List;

import telran.java40.students.dto.ScoreDto;
import telran.java40.students.dto.StudentDto;
import telran.java40.students.dto.StudentsCredentialsDto;
import telran.java40.students.dto.UpdateStudentDto;

public interface StudentService {
	
	boolean addStudent(StudentsCredentialsDto studentCredentialDto);
	
	StudentDto findStudent(Integer id);
	
	StudentDto deleteStudent(Integer id);
	
	StudentsCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto);
	
	boolean addScore(Integer id, ScoreDto scoreDto);
	
	List<StudentDto> findStudentsByName(String name);
	
	long getStudentsNamesQuantity(List<String> names);
	
	List<StudentDto> getStudentsByExamScore(String exam, int minScore);
	
}

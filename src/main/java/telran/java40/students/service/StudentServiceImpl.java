package telran.java40.students.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java40.students.dao.StudentRepository;
import telran.java40.students.dto.ScoreDto;
import telran.java40.students.dto.StudentDto;
import telran.java40.students.dto.StudentsCredentialsDto;
import telran.java40.students.dto.UpdateStudentDto;
import telran.java40.students.dto.exeptions.StudentNotFoundExeption;
import telran.java40.students.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	StudentRepository studentRepository;
	ModelMapper modelMapper;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean addStudent(StudentsCredentialsDto studentCredentialDto) {
		if (studentRepository.existsById(studentCredentialDto.getId())) {
			return false;
		}
		studentRepository.save(modelMapper.map(studentCredentialDto, Student.class));
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		return modelMapper.map(studentRepository.findById(id).orElseThrow(StudentNotFoundExeption::new),
				StudentDto.class);
	}

	@Override
	public StudentDto deleteStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundExeption::new);
		studentRepository.deleteById(id);
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentsCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundExeption::new);
		student.setName(updateStudentDto.getName());
		student.setPassword(updateStudentDto.getPassword());
		return modelMapper.map(studentRepository.save(student), StudentsCredentialsDto.class);
	}

	@Override
	public boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundExeption::new);
		boolean returnValue = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return returnValue;
	}

	@Override
	public List<StudentDto> findStudentsByName(String name) {
		return studentRepository.findByNameIgnoreCase(name)
				.map(s -> modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public long getStudentsNamesQuantity(List<String> names) {
		return studentRepository.countByNameIn(names);
	}

	@Override
	public List<StudentDto> getStudentsByExamScore(String exam, int minScore) {
		return studentRepository.findByExamAndScoreGreaterEqualsThan(exam, minScore)
				.map(s -> modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
	}
	
}

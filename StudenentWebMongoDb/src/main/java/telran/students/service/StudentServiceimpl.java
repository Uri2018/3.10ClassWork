package telran.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.students.dao.IStudentRepository;
import telran.students.dto.NotFoundStudentException;
import telran.students.dto.ScoreDto;
import telran.students.dto.StudentDto;
import telran.students.dto.StudentEditDto;
import telran.students.dto.StudentResponseDto;
import telran.students.entities.Student;
@Service
public class StudentServiceimpl implements IStudentService{
@Autowired
IStudentRepository studentRepository;
	@Override
	public boolean addStudent(StudentDto studentdto) {
		Student student=studentRepository.findById(studentdto.getId()).orElse(null);
		if(student!=null)
		{
			return false;
		}
		
		student=new Student(studentdto.getId(),studentdto.getName(),studentdto.getPassword());
		 studentRepository.save(student);
		return true;
	}

	@Override
	public StudentResponseDto deleteStudent(int id) {
		Student student=studentRepository.findById(id).orElse(null);
		if(student==null)
		{
			return null;
		}
		studentRepository.deleteById(id);
		return studentToStudentResponseDto(student);
	}

	private StudentDto studentToStudentDto(Student student) {
		return StudentDto.builder().id(student.getId()).name(student.getName()).password(student.getName()).build();
	}
private StudentResponseDto studentToStudentResponseDto(Student student)
{
	return StudentResponseDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores()).build();
}
	@Override
	public StudentDto editStudent(int id, StudentEditDto studentDto) {
	Student student=studentRepository.findById(id).orElse(null);
	if(student==null)
	{
		return null;
	}
	if(studentDto.getName()!=null)
	{
		student.setName(studentDto.getName());
	}
	if(studentDto.getPassword()!=null)
	{
		student.setPassword(studentDto.getPassword());
	}
	studentRepository.save(student);
		return studentToStudentDto(student);
	}

	@Override
	public StudentResponseDto getStudent(int id) {
		Student student =studentRepository.findById(id).orElse(null);
		if(student==null)
		{
			return null;
		}
		
		return studentToStudentResponseDto(student);
	}

	@Override
	public boolean addScore(int id, ScoreDto score) {
		Student student=studentRepository.findById(id).orElse(null);
		if(student==null)
		{
			throw new NotFoundStudentException();
		}
		boolean res=student.addscore(score.getExamName(), score.getScore());
		studentRepository.save(student);
		return res;
	}

	@Override
	public List<Student> findByNameStartWith(String name) {
		return studentRepository.findByNameRegex(name+".*");
	}

}

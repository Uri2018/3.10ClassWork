package telran.students.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import telran.students.entities.Student;
@Repository
public class StudentRepository implements IStudentRepository {
Map<Integer,Student>students=new ConcurrentHashMap<>();

	@Override
	public boolean addStudent(Student student) {
		return students.putIfAbsent(student.getId(), student)==null;
	}

	@Override
	public Student removeStudent(int id) {
		return students.remove(id);
	}

	@Override
	public Student getStudentById(int id) {
		return students.get(id);
	}

	@Override
	public Student editStudent(Student student) {
		return students.replace(student.getId(), student);
	}

	
}

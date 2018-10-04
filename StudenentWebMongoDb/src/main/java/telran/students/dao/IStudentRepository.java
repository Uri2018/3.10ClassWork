package telran.students.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import telran.students.api.StudentURI;
import telran.students.dto.StudentResponseDto;
import telran.students.entities.Student;

public interface IStudentRepository extends MongoRepository<Student, Integer>{
List<Student> findByNameRegex(String regex);
	
}

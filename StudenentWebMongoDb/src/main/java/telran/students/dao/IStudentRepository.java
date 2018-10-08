package telran.students.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import telran.students.api.StudentURI;
import telran.students.dto.StudentResponseDto;
import telran.students.entities.Student;

public interface IStudentRepository extends MongoRepository<Student, Integer>{

List<Student> findByNameRegex(String regex);
@Query ("{'name ': {$gt : ?0 }}")
Stream<Student>findByAfterAlefBet(String str);	

Stream<Student>findAllBy();

@Query("{?0:{$gt:?1}}")
List<Student>findByDynamicField(String exam,int score);


}

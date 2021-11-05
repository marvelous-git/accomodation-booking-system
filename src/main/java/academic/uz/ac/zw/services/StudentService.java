package academic.uz.ac.zw.services;

import academic.uz.ac.zw.domain.BoardingHouse;
import academic.uz.ac.zw.domain.Student;
import academic.uz.ac.zw.repository.BoardingHouseRepository;
import academic.uz.ac.zw.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public Optional<Student> findByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}

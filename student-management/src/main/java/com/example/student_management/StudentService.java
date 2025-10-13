package com.example.student_management;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        else{
            throw new RuntimeException("Student not found with id: "+ id);
        }
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }


    public Student updateStudent(int id , Student updatestudent){
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The student was not found"));
        existing.setName(updatestudent.getName());
        existing.setAge(updatestudent.getAge());
        existing.setEmail(updatestudent.getEmail());
        existing.setEnrollmentDate(updatestudent.getEnrollmentDate());

        return studentRepository.save(existing);
    }

    public void deleteStudent(int id){
        if(!studentRepository.existsById(id)){
            throw new RuntimeException("No such Student");
        }
        studentRepository.deleteById(id);
    }


    public List<Student> findByCourse(String course){
        return studentRepository.findByCourse(course);
    }

}

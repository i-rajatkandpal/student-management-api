package com.example.student_management;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }


    // StudentService.java
    public Student updateStudent(int id, Student updatedStudent) {
        // Fetch existing student or throw 404
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        if (!existing.getEmail().equals(updatedStudent.getEmail()) &&
                studentRepository.existsByEmail(updatedStudent.getEmail())) {
            throw new DuplicateEmailException(updatedStudent.getEmail());
        }

        existing.setName(updatedStudent.getName());
        existing.setAge(updatedStudent.getAge());
        existing.setCourse(updatedStudent.getCourse());
        existing.setEmail(updatedStudent.getEmail());
        existing.setEnrollmentDate(updatedStudent.getEnrollmentDate());

        return studentRepository.save(existing);
    }



    public void deleteStudent(int id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }


    public List<Student> findByCourse(String course){
        return studentRepository.findByCourse(course);
    }

    public StudentResponseDTO convertToDTO(Student student){
        StudentResponseDTO studentDTO = new StudentResponseDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setCourse(student.getCourse());
        studentDTO.setEnrollmentDate((student.getEnrollmentDate()));

        return studentDTO;
    }

    public Student convertToStudent(StudentRequestDTO studentRequestDTO){
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setCourse(studentRequestDTO.getCourse());
        student.setAge(studentRequestDTO.getAge());
        student.setEnrollmentDate(LocalDate.now());
        return student;
    }

    public static class StudentNotFoundException extends RuntimeException {
        public StudentNotFoundException(int id) {
            super("Student not found with id: " + id);
        }
    }

    public static class DuplicateEmailException extends RuntimeException {
        public DuplicateEmailException(String email) {
            super("Email must be unique: " + email);
        }
    }

}

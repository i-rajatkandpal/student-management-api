package com.example.student_management;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
        public  List<StudentResponseDTO> getStudents(){
            return studentService.getAllStudents().
                    stream().map(studentService::convertToDTO).toList();
        }

    @GetMapping("/students/{id}")
    public StudentResponseDTO getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        return studentService.convertToDTO(student);
    }

    @PostMapping("/students")
    public StudentResponseDTO postStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        Student student = studentService.convertToStudent(studentRequestDTO);
        Student savedStudent = studentService.createStudent(student);
        return studentService.convertToDTO(savedStudent);
    }


    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable int id, @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        Student updatedStudent = studentService.convertToStudent(studentRequestDTO);
        return studentService.updateStudent(id, updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Deleted";
    }

    @GetMapping("/students/search")
    public List<StudentResponseDTO> searchByCourse(@RequestParam String course) {
        List<Student> students = studentService.findByCourse(course);
        return students.stream()
                .map(studentService::convertToDTO)
                .toList();
    }



}

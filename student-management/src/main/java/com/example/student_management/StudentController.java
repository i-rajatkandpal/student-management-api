package com.example.student_management;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

// without the DTO exposing every field
//    @GetMapping("/students")
//    public List<Student> getStudents(){
//        return studentService.getAllStudents();
//    }

    @GetMapping("/students")
        public  List<StudentResponseDTO> getStudents(){
            return studentService.getAllStudents().
                    stream().map(studentService::convertToDTO).toList();
        }

    @GetMapping("/students/{id}")
    public Student getStudentsById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    // without requestDTO
    // @PostMapping("/students")
//    public Student postStudent(@RequestBody Student student){
//        return studentService.createStudent(student);
//    }


    @PostMapping("/students")
    public StudentResponseDTO postStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        Student savedStudent = studentService.convertToStudent(studentRequestDTO);
        return studentService.convertToDTO(savedStudent);
    }


    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student){
        return studentService.updateStudent(id,student);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Deleted";
    }

    @GetMapping("/students/search")
    public List<Student> searchByCourse(@RequestParam String course){
        return studentService.findByCourse(course);
    }
}

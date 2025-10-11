package com.example.student_management;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/students")
    public String getStudents(){
        return "Getting all the students";
    }

    @GetMapping("/students/{id}")
    public String getStudentsById(@PathVariable long id){
        return "Getting the Student with the id " + id ;
    }
    @PostMapping("/students")
    public String postStudent(@RequestBody String name){
        return "Created a student named " + name;
    }

    @PutMapping("/students/{id}")
    public String updateStudent(@PathVariable long id, @RequestBody String name){
        return "Updated the student with id: " + id + " and name: " + name;
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return "Student with ID " + id + " has been deleted.";
    }

    @GetMapping("/students/search")
    public String searchByCourse(@RequestParam String course){
        return "Students with Course = " + course;
    }
}

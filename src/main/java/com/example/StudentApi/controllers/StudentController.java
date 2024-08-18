package com.example.StudentApi.controllers;

import com.example.StudentApi.models.StudentModel;
import com.example.StudentApi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")




public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public StudentModel createStudent(@RequestBody StudentModel student) {
        return service.createStudent(student);
    }

    @GetMapping
    public List<StudentModel> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentModel> getStudentById(@PathVariable UUID id) {
        Optional<StudentModel> student = service.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentModel> updateStudent(@PathVariable UUID id, @RequestBody StudentModel student) {
        return ResponseEntity.ok(service.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
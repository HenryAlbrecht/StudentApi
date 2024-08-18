package com.example.StudentApi.services;

import com.example.StudentApi.models.StudentModel;
import com.example.StudentApi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public StudentModel createStudent(StudentModel student) {
        return repository.save(student);
    }

    public List<StudentModel> getAllStudents() {
        return repository.findAll();
    }

    public Optional<StudentModel> getStudentById(UUID id) {
        return repository.findById(id);
    }

    public StudentModel updateStudent(UUID id, StudentModel student) {
        student.setId(id);
        return repository.save(student);
    }

    public void deleteStudent(UUID id) {
        repository.deleteById(id);
    }
}
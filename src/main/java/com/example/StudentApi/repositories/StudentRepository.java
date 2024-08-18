package com.example.StudentApi.repositories;

import com.example.StudentApi.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentModel, UUID> {
}
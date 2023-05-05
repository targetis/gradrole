package com.targetis.gradrole.service;

import com.targetis.gradrole.domain.Student;
import com.targetis.gradrole.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepo;

    public List<Student> getAllstudent() {
        List<Student> studentList = new ArrayList<>();
        studentRepo.findAll().forEach(student -> studentList.add(student));

        return studentList;
    }

    public Student getStudentById(Long id) {
        return studentRepo.findById(id).get();
    }

    public boolean saveAndUpdateStudent(Student student) {
        Student updatedStudent = studentRepo.save(student);

        if (studentRepo.findById(updatedStudent.getId()) != null) {
            return true;
        }

        return false;
    }

    public boolean deleteStudent(Long id) {
        studentRepo.deleteById(id);

        if (studentRepo.findById(id) != null) {
            return true;
        }

        return false;
    }
}

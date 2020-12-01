package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.Student;
import com.unibuc.demo.dto.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student convertStudentFrom(StudentDTO studentDTO){
        return Student.builder()
                .CNP(studentDTO.getCNP())
                .name(studentDTO.getName())
                .birthDate(studentDTO.getBirthDate())
                .email(studentDTO.getEmail())
                .build();
    }

    public StudentDTO convertStudentDTOFrom(Student student){
        return StudentDTO.builder()
                .CNP(student.getCNP())
                .name(student.getName())
                .birthDate(student.getBirthDate())
                .email(student.getEmail())
                .build();
    }
}

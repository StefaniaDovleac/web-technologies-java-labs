package com.unibuc.demo.service;

import com.unibuc.demo.domain.Student;
import com.unibuc.demo.dto.StudentDTO;
import com.unibuc.demo.mapper.StudentMapper;
import com.unibuc.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentDTO> getAll() {
        return studentRepository.getAll().stream().map(studentMapper::convertStudentDTOFrom).collect(Collectors.toList());
    }

    public StudentDTO getStudentByCNP(String CNP) {
        return studentMapper.convertStudentDTOFrom(studentRepository.getStudentByCNP(CNP).get());
    }

    public void createStudent(StudentDTO studentDTO) {
        LocalDate date = LocalDate.parse(studentDTO.getBirthDate());
//        studentDTO.setBirthDate(date);
        studentRepository.save(studentMapper.convertStudentFrom(studentDTO));
    }

    public void deleteStudentByCNP(String CNP) {
        Student studentFound = studentRepository.getStudentByCNP(CNP).get();
        studentRepository.deleteStudentByCNP(studentFound);
    }

    public void updateStudent(StudentDTO studentDTO) {
        Student studentFound = studentRepository.getStudentByCNP(studentDTO.getCNP()).get();
        studentRepository.deleteStudentByCNP(studentFound);
        studentRepository.save(studentMapper.convertStudentFrom(studentDTO));
    }
}

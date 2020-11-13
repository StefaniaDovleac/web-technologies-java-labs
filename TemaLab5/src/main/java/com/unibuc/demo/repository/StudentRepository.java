package com.unibuc.demo.repository;

import com.unibuc.demo.domain.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private final List<Student> studentList = new ArrayList<>();

    public List<Student> getAll() {
        return studentList;
    }

    public void createAndSave(String CNP, String name, String birthDate, String email) {
        Student student = Student.builder()
                .CNP(CNP)
                .name(name)
                .email(email)
                .birthDate(birthDate)
                .build();

        studentList.add(student);
    }


    public Optional<Student> getStudentByCNP(String CNP) {
        return studentList.stream().filter(student -> student.getCNP().equals(CNP)).findFirst();
    }

    private void setUpContextForPersonRepository() {
        createAndSave("1234567891234", "Popescu", "13-05-1999", "popescu@yahoo.com");
        createAndSave("9876543219876", "Ionescu", "21-09-2000", "ionescu@gmail.com");
        createAndSave("5684125846657", "Georgescu", "11-11-1996", "georgescu@gmail.com");
    }

    public void save(Student student) {
        studentList.add(student);
    }

    public void deleteStudentByCNP( Student student){
        studentList.remove(student);
    }

    @PostConstruct
    public void afterPropertiesSettings() {
        setUpContextForPersonRepository();
    }
}

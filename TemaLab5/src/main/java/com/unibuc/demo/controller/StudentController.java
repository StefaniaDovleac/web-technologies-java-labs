package com.unibuc.demo.controller;

import com.unibuc.demo.dto.StudentDTO;
import com.unibuc.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<StudentDTO> studentDTOList = this.studentService.getAll();
        model.addAttribute("studentDTOList", studentDTOList);
        return "view-students";
    }

    @GetMapping("/{CNP}")
    public String getStudentByCNP(@PathVariable("CNP") String CNP, Model model) {
        StudentDTO studentDTO = this.studentService.getStudentByCNP(CNP);
        model.addAttribute("studentDTO", studentDTO);
        return "view-student";
    }

    @GetMapping("/view-create")
    public String viewCreate(StudentDTO studentDTO) {
        return "add-student";
    }

    @PostMapping("/create")
    public String createStudent(@Valid StudentDTO studentDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }
        studentService.createStudent(studentDTO);
        model.addAttribute("studentDTOList", studentService.getAll());
        return "view-students";
    }

    @GetMapping("/view-update/{CNP}")
    public String viewUpdate(@PathVariable String CNP, StudentDTO studentDTO, Model model){
        StudentDTO studentDTOFound = studentService.getStudentByCNP(CNP);
        model.addAttribute("studentDTO", studentDTOFound);
        return "update-student";
    }

    @PostMapping("/update/{CNP}")
    public String updateStudent(@PathVariable String CNP, @Valid StudentDTO studentDTO, BindingResult result, Model model) {
        System.out.println(result);
        if(result.hasErrors()){
            return "update-student";
        }

        studentService.updateStudent(studentDTO);
        model.addAttribute("studentDTO", studentService.getStudentByCNP(CNP));
        return "view-student";
    }

    @GetMapping("/delete/{CNP}")
    public String deleteStudentByCNP(@PathVariable String CNP, Model model) {
        studentService.deleteStudentByCNP(CNP);
        List<StudentDTO> studentDTOList = this.studentService.getAll();
        model.addAttribute("studentDTOList", studentDTOList);
        return "view-students";
    }
    //TEMA Creati un proiect spring boot (file->new project-> spring initializer)
    // 1. Modelul va contine minim 3 atribute cu validari specifice
    // 2. Simulati baza de date la nivel de repository
    // 3. Logica va fi dezvoltata la nivelul serviciului
    // 4. Maparea domain-dto se va realiza la nivelul controllerului
    // 5. Creati end-point-uri pentru urmatoarele functionalitati: create, get all, get info, update si delete
    // 6. Fiecare endpoint va returna view-ul specific fiecarei functionalitati

    //Observatii:
    //Pentru fiecare endpoint de tip POST este necesar sa faceti redirect la view-ul ce contine methoda post prin intermediul unui
    //endpoint GET ce primeste ca parametru obiectul pe care vrem sa-l modelam (th:object${myObject})
}

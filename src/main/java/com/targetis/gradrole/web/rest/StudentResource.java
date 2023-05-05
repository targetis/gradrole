package com.targetis.gradrole.web.rest;

import com.targetis.gradrole.domain.Student;
import com.targetis.gradrole.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class StudentResource {

    @Autowired
    StudentService studentService;

    @GetMapping({ "/studentList" })
    public String viewStudentList(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("studentList", studentService.getAllstudent());
        model.addAttribute("message", message);

        return "studentList";
    }

    @GetMapping("/addStudent")
    public String addStudent(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("message", message);

        return "AddStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(Student student, RedirectAttributes redirectAttributes) {
        if (studentService.saveAndUpdateStudent(student)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/StudentList";
        }

        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addStudent";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));

        return "EditStudent";
    }

    @PostMapping("/editSavStudent")
    public String editSaveStudent(Student student, RedirectAttributes redirectAttributes) {
        if (studentService.saveAndUpdateStudent(student)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            return "redirect:/StudentList";
        }

        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editStudent/" + student.getId();
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (studentService.deleteStudent(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Delete Failure");
        }

        return "redirect:/students";
    }
}

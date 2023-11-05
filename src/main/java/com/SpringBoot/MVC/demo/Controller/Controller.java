package com.SpringBoot.MVC.demo.Controller;

import com.SpringBoot.MVC.demo.Model.Student;
import com.SpringBoot.MVC.demo.Repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller


public class Controller {
    @Autowired
    DemoRepository repository;

    // Return list of Students
    @GetMapping(value = "/homePage")
    public String getAllPatients(Model model)
    {
        List<Student> studentList = repository.getStudents();
        model.addAttribute("Students",studentList);
        return "homePage";
    }

    // Get by parameter on url
    @GetMapping(value = "/allStudents/{name}")
    public String getStudentByName(@PathVariable(name = "name") String name, Model model)
    {
        List<Student> studentList = repository.getStudent(name);
        model.addAttribute("Students",studentList);
        return "studentListByName";
    }

    // Add by use view form input
//    @GetMapping(value = "/formSearch")
//    public String formSearch()
//    {
//        return "formSearch";
//    }

    // search by view engine
    @GetMapping(value = "/search")
    public String findStudentByName(@RequestParam("name") String name, Model model)
    {
        List<Student> studentList = repository.getStudent(name);
        model.addAttribute("Students",studentList);
        return "homePage";
    }

    // Add by rest api
//    @PostMapping(value = "/addNewStudent")
//    // Add student to list
//    public ResponseEntity addStudent(@RequestBody Student student)
//    {
//        repository.addStudent(student);
//        return ResponseEntity.ok("Add successfully");
//    }

    // Add by view thymleaf
    @GetMapping(value = "homePage/editForm")
    public String addForm(Model model)
    {
        model.addAttribute("student",new Student());
        return "edit";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") Student student)
    {
        repository.addStudent(student);
        return "redirect:/homePage";
    }

    //Delete
    @DeleteMapping(value = "/homePage/{id}/delete")
    public String deleteStudent(@PathVariable(name = "id") int id)
    {
        Student student = repository.findById(id);
        if (student!=null)
        {
            repository.deleteById(id);
        }
        return "redirect:/homePage";
    }

}

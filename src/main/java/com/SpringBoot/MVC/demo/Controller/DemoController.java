package com.SpringBoot.MVC.demo.Controller;

import com.SpringBoot.MVC.demo.DTO.Student;
import com.SpringBoot.MVC.demo.Repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class DemoController {
    @Autowired
    DemoRepository demoRepository;

    public DemoController(DemoRepository demoRepository)
    {
        this.demoRepository=demoRepository;
    }

    @GetMapping(value="/student")
    public List<Student> listStudent()
    {
        return demoRepository.getStudents();
    }

    @GetMapping(value = "student/{studentName}")
    public List<Student> getStudentName(@PathVariable(name="studentName") String name)
    {
        return demoRepository.getStudent(name);
    }

    @GetMapping(value = "/getAverageGPA")
    public double getAverageGPA()
    {
        return demoRepository.getAverageGPA();
    }

    @GetMapping(value = "/maxGPA")
    public Student getMaxGPA()
    {
        return demoRepository.getMaxGPA();
    }

//    @PostMapping(value = "/addStudent")
//    public ResponseEntity addStudent(@RequestBody Student student)
//    {
////        if (!student.getName().isEmpty()&&(student.getCgpa()>0))
////        {
////            Student added = demoRepository.addStudent(student);
////            return ResponseEntity.ok("Add successfully!");
////        }
////        return ResponseEntity.badRequest().body("Invalid student data");
//        Student added = demoRepository.addStudent(student);
//        return ResponseEntity.ok("Add successfully!");
//    }

    @GetMapping(value = "/sortedListByGPA")
    public List<Student> sortListGPA()
    {
        return demoRepository.sortGPA();
    }

    @GetMapping(value = "/findById/{Id}")
    public Student findById(@PathVariable(name="Id") int id)
    {
        return demoRepository.findById(id);
    }

    @PostMapping(value = "/addStudent")
    public ResponseEntity addStudent(@RequestBody Student student)
    {
        demoRepository.addStudent(student);
        return ResponseEntity.ok(student);
    }



    @PutMapping(value = "/student/{id}/{gpa}")
    public Student updateById(@PathVariable(name="id") int id, @PathVariable(name = "gpa") double newGPA)
    {
        return demoRepository.updateById(id,newGPA);
    }

    @DeleteMapping(value = "/student/{id}")
    public Student deleteById(@PathVariable(name="id") int id)
    {
        return demoRepository.deleteById(id);
    }

}

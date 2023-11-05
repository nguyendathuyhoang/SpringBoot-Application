package com.SpringBoot.MVC.demo.Repository;

import com.SpringBoot.MVC.demo.DTO.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DemoRepository {

    private List<Student> studentList = new ArrayList<>(Arrays.asList(new Student(1,"Hoang",9.8),
            new Student(2,"MyAnh",8.0),
            new Student(3,"HoangHa",5.5),
            new Student(5,"HuyThanh",7.7)));


    public List<Student> getStudents()
    {
        return studentList;
    }

    public List<Student> getStudent(String name)
    {
        List<Student> result = new ArrayList<>();
        for (Student student1:studentList)
        {
            if (student1.getName().equals(name))
            {
                result.add(student1);
            }
        }
        return result;
    }

    public double getAverageGPA()
    {
        double count = studentList.stream().count();
        double sumGPA = studentList.stream().mapToDouble(Student::getCgpa).sum();
        double sumGPA1 = studentList.stream().map(Student::getCgpa).reduce(0.0,Double::sum);
        return sumGPA1/count;
    }
    
    public Student getMaxGPA()
    {
        Student studentMaxGPA = studentList.stream()
                .max(Comparator.comparingDouble(Student::getCgpa)).get();

        return studentMaxGPA;
    }

    // not done
    public String addStudent(Student student)
    {

        studentList.add(student);
        return "Adding student successfully!";
    }

    public List<Student> sortGPA()
    {
        List<Student> listSort = new ArrayList<>();
        listSort = studentList.stream().sorted(Comparator.comparingDouble(Student::getCgpa)).collect(Collectors.toList());
        return listSort;
    }

    public Student findById(int id)
    {
        for (Student student:studentList)
        {
            if (student.getId()==id)
            {
                return student;
            }
        }
        return null;
    }

    public Student updateById(int id, double newGPA)
    {
        for (Student student:studentList)
        {
            if (student.getId()==id)
            {
                student.setCgpa(newGPA);
                return student;
            }
        }
        return null;
    }

    public Student deleteById(int id)
    {
        Student student = studentList.stream().filter(s->s.getId()==id).findAny().get();
        studentList.remove(student);
        return student;
    }
}

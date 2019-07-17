package com.github.department.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity // This tells Hibernate to make a table out of this class
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Имя не может быть пустым")
    @Length(max = 255, message= "Имя превышает допустимую длину")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "Введите отдел")
    private Department department;
    @NotNull(message = "Введите зарплату")
    private Double salary;
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    public Employee() {
    }

    public Employee(String name, Department department, Double salary, User user) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.author = user;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getFormattedSalary() {
        return java.text.NumberFormat.getCurrencyInstance().format(salary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

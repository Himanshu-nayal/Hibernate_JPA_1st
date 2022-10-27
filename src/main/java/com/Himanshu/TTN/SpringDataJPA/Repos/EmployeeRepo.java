package com.Himanshu.TTN.SpringDataJPA.Repos;

import com.Himanshu.TTN.SpringDataJPA.Entity.Employee;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee,Integer> {

    List<Employee> findByName(String name);
    List<Employee> findByNameAndAge(String name, int age);
    List<Employee> findByAgeBetween(int age1,int age2);
    List<Employee> findByNameLike(String name);
    List<Employee> FindPagingAndSorting(int age);

}

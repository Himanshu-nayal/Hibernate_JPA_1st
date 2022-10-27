package com.Himanshu.TTN.SpringDataJPA;

import com.Himanshu.TTN.SpringDataJPA.Entity.Employee;
import com.Himanshu.TTN.SpringDataJPA.Repos.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringDataJpaApplicationTests {
	@Autowired
	EmployeeRepo employeeRepo;
	//create employee
	@Test
	public void testCreate() {
		Employee employee=new Employee();
		employee.setName("Himanshu");
		employee.setAge(21);
		employee.setLocation("Uttarakhand");

		Employee employee1=new Employee();
		employee1.setName("aman");
		employee1.setAge(30);
		employee1.setLocation("noida");
		Employee employee2=new Employee();
		employee2.setName("kamlesh");
		employee2.setAge(29);
		employee2.setLocation("bihar");
		employeeRepo.save(employee2);

		employeeRepo.save(employee);
		employeeRepo.save(employee1);

	}
	//Update employee

	@Test
	public void testUpdate(){
		Employee employee = employeeRepo.findById(1).get();
		employee.setAge(22);
		employeeRepo.save(employee);

	}
	//read employee
	@Test
	public void testRead(){
		Employee employee1=employeeRepo.findById(2).get();
		assertNotNull(employee1);
		assertEquals(23,employee1.getAge());
	}
	//delete employee
    @Test
	public void testDelete() {
		if (employeeRepo.existsById(101) ){
			employeeRepo.deleteById(101);
		}
	}
	//count total no of employee
	public void testCount(){
		System.out.println("total records" +employeeRepo.count());
	}

	// Create and use finder to find Employee by Name
	@Test
	public void TestFindByName(){
		List<Employee> employees = employeeRepo.findByName("aman");
		employees.forEach(e-> System.out.println(e.getId()));
	}
	//Create and use finder to find Employees starting with A character
	@Test
	public void TestFindByNameLike(){
		List<Employee> employees = employeeRepo.findByNameLike("A%");
		employees.forEach(employee -> System.out.println(employee.getName()));
	}



	//Create and use finder to find Employees Between the age of 28 to 32
	@Test
	public void testFindByAgeBetween(){
		List<Employee> employees = employeeRepo.findByAgeBetween(28, 32);
		employees.forEach(e-> System.out.println(e.getName()));

	}
	//Implement Pagination and Sorting on the bases of Employee Age
	@Test
	public void testByPagination(){
		Pageable pageable= PageRequest.of(0, 2);
		Page<Employee> emp = employeeRepo.findAll(pageable);
		emp.forEach(employee -> System.out.println(employee.getName()));
	}
	@Test
	public void testFindPagingAndSorting(){
			Pageable pageable =  PageRequest.of(0,2, Sort.Direction.DESC,"age");
			employeeRepo.findAll(pageable).forEach(p-> System.out.println(p.getAge()));


	}
}

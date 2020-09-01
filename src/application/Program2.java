package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		DepartmentDao departmentDao=DaoFactory.createDepartmentDao();
		System.out.println("===TEST 1: Department find by id===");
		Department department =  departmentDao.findById(2);
		System.out.println(department);
		
		System.out.println("===TEST 3: Department find all===");
		List<Department> list=new ArrayList<>();
		list=departmentDao.findAll();
		for(Department obj:list) {
			System.out.println(obj);
		}
		
		/*System.out.println("===TEST 4: Insert Department===");
		Department newDepartment=new Department(null, "D3");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted: New id is: "+newDepartment.getId());*/
		
		System.out.println("===TEST 5: Update Department===");
		department=departmentDao.findById(8);
		department.setName("D4");
		departmentDao.update(department);
		System.out.println("Update complete!");
		
		/*System.out.println("===TEST 6: Seller Delete===");
		departmentDao.delete(12);
		System.out.println("Delete Complete");*/

	}

}

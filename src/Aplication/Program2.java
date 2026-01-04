package Aplication;

import Model.Dao.DaoFactory;
import Model.Dao.DepartmentDao;
import Model.Entities.Department;

import java.util.List;

public class Program2 {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.creatDepartmentDao();

        System.out.println("\n====Teste 1 FindById ====");
        Department d1 = departmentDao.findById(2);
        System.out.println(d1);

        System.out.println("\n====Teste 2 FindById ====");
        List<Department> list = departmentDao.findAll();
        for(Department d : list){
            System.out.println(d);
        }

        System.out.println("\n====Teste 3 FindById ====");
        Department d2 = new Department(null, "Drinks");
        departmentDao.insert(d2);


    }
}

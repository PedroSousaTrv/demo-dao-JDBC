package Aplication;

import Model.Dao.DaoFactory;
import Model.Dao.DepartmentDao;
import Model.Entities.Department;

public class Program2 {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.creatDepartmentDao();

        System.out.println("====Teste 1 FindById ====");
        Department d1 = departmentDao.findById(2);
        System.out.println(d1);

        

    }
}

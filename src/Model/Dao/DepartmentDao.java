package Model.Dao;

import Model.Entities.Department;

import java.util.List;

public interface DepartmentDao {

    void insert(Department obj);
    void update(Department obj);
    void deleteBuId(Integer obj);
    Department findById(Integer obj);
    List<Department> findAll();
}

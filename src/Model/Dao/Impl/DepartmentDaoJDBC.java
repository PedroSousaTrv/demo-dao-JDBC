package Model.Dao.Impl;

import Model.Dao.DepartmentDao;
import Model.Entities.Department;
import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {

    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteBuId(Integer obj) {

    }

    @Override
    public Department findById(Integer obj) {

        PreparedStatement  st = null;
        ResultSet rs = null;

        try{

            st = conn.prepareStatement(
                    "SELECT * from department "
                    +"WHERE Id = ? "
                    +"ORDER BY Name"
            );

            st.setInt(1,obj);
            rs = st.executeQuery();

            if(rs.next()){

                return intantiationDepartment(rs);

            }else{
                return null;
            }


        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }


    private Department intantiationDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}

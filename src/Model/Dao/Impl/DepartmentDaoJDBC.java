package Model.Dao.Impl;

import Model.Dao.DepartmentDao;
import Model.Entities.Department;
import db.DB;
import db.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO department "
                            + "(Name) "
                            + "VALUES "
                            +"(?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1,obj.getName());
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();

                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }

                DB.closeResultSet(rs);
            }else{
                throw new DbException("Error unexpected");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }


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

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * from department "
            );

            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();

            while (rs.next()){
                list.add(intantiationDepartment(rs));
            }

            return list;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }


    }


    private Department intantiationDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}

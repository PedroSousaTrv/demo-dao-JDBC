package Model.Dao.Impl;

import Model.Dao.SellerDao;
import Model.Entities.Department;
import Model.Entities.Seller;
import db.DB;
import db.DbException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao{

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getDate().getYear()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("unexpected Error! No rows Affected");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteBuId(Integer obj) {

    }

    @Override
    public Seller findById(Integer obj) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    +"ON seller.DepartmentId = department.Id "
                    +"WHERE seller.Id = ?"
            );

            st.setInt(1,obj);
            rs = st.executeQuery();

            if(rs.next()){

                Department dep = intantiationDepartment(rs);
                return intantiationSeller(rs, dep);

            }else{
                return null;
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
        }

    }

    private Department intantiationDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller intantiationSeller(ResultSet rs, Department dep) throws SQLException {
        Seller sel = new Seller();
        sel.setId(rs.getInt("Id"));
        sel.setName(rs.getString("Name"));
        sel.setEmail(rs.getString("Email"));
        sel.setBaseSalary(rs.getDouble("BaseSalary"));
        sel.setDate(rs.getObject("BirthDate", LocalDateTime.class));
        sel.setDepartment(dep);
        return sel;
    }

    @Override
    public List<Seller> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            +"FROM seller INNER JOIN department "
                            +"ON seller.DepartmentId = department.Id "
                            +"ORDER BY Name"
            );

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer,Department> map = new HashMap<>();

            while (rs.next()){

                Department dep = map.get(rs.getInt("DepartmentID"));

                if(dep == null){
                    dep = intantiationDepartment(rs);
                    map.put(rs.getInt("DepartmentID"), dep);
                }

                list.add(intantiationSeller(rs,dep));

            }
            return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Seller> findByDepartment(Department department) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    +"FROM seller INNER JOIN department "
                    +"ON seller.DepartmentId = department.Id "
                    +"WHERE DepartmentId = ? "
                    +"ORDER BY Name"
            );

            st.setInt(1,department.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer,Department> map = new HashMap<>();

            while (rs.next()){

                Department dep = map.get(rs.getInt("DepartmentID"));

                if(dep == null){
                    dep = intantiationDepartment(rs);
                    map.put(rs.getInt("DepartmentID"), dep);
                }

                list.add(intantiationSeller(rs,dep));

            }
            return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
        }

    }
}

package Model.Dao.Impl;

import Model.Dao.SellerDao;
import Model.Entities.Department;
import Model.Entities.Seller;
import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class SellerDaoJDBC implements SellerDao{

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

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

                Department dep = new Department();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));
                Seller sel = new Seller();
                sel.setId(rs.getInt("Id"));
                sel.setName(rs.getString("Name"));
                sel.setEmail(rs.getString("Email"));
                sel.setBaseSalary(rs.getDouble("BaseSalary"));
                sel.setDate(rs.getObject("BirthDate", LocalDateTime.class));
                sel.setDepartment(dep);
                return sel;
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

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}

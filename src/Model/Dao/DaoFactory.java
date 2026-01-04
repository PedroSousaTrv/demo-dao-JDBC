package Model.Dao;

import Model.Dao.Impl.DepartmentDaoJDBC;
import Model.Dao.Impl.SellerDaoJDBC;
import db.DB;

public class DaoFactory {

    public static SellerDao creatSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao creatDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }

}

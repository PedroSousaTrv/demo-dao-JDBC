package Model.Dao;

import Model.Dao.Impl.SellerDaoJDBC;
import db.DB;

public class DaoFactory {

    public static SellerDao creatSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
}

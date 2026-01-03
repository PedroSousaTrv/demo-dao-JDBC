package Aplication;

import Model.Dao.DaoFactory;
import Model.Dao.SellerDao;
import Model.Entities.Department;
import Model.Entities.Seller;
import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.creatSellerDao();
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

    }
}

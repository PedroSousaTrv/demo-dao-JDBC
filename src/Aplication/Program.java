package Aplication;

import Model.Dao.DaoFactory;
import Model.Dao.SellerDao;
import Model.Entities.Department;
import Model.Entities.Seller;
import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.creatSellerDao();

        System.out.println("==== TESTE 1: seller findbyId ====");
        Seller seller = sellerDao.findById(3);

        System.out.println("\n==== TESTE 1: seller findByDeparment  ====");
        Department  department = new Department(2,null);
        List<Seller>  list = sellerDao.findByDepartment(department);

        for(Seller sel : list){
            System.out.println(sel);
        }

        System.out.println(seller);

    }
}

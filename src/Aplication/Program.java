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

        System.out.println("\n==== TESTE 2: seller findByDeparment  ====");
        Department  department = new Department(2,null);
        List<Seller>  list = sellerDao.findByDepartment(department);

        for(Seller sel : list){
            System.out.println(sel);
        }

        System.out.println(seller);

        System.out.println("\n==== TESTE 3: seller findAll  ====");
        List<Seller> list2 = sellerDao.findAll();

        for (Seller sel : list2){
            System.out.println(sel);
        }

        System.out.println("\n==== TESTE 4: seller Insert  ====");
        Seller seller1 = new Seller(null,"greg", "greg@gmail.com", LocalDateTime.now(), 3022.0, department);
        sellerDao.insert(seller1);
        System.out.println("Inserted! New Id = "+ seller1.getId());

        System.out.println("\n==== TESTE 5: seller Update  ====");
        seller = sellerDao.findById(1);
        seller.setName("Martha sdda");
        sellerDao.update(seller1);

        System.out.println("\n==== TESTE 5: seller Delete  ====");
        sellerDao.deleteById(2);
    }
}

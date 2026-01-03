package Aplication;

import Model.Entities.Department;
import Model.Entities.Seller;
import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Program {
    public static void main(String[] args) {

        Department d1 = new Department(1,"Books");
        Seller seller = new Seller(21,"pedro","pedro@gmail.com", LocalDateTime.now(),300.0,d1);
        System.out.println(d1);
        System.out.println(seller);

    }
}

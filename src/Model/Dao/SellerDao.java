package Model.Dao;

import Model.Entities.Seller;

import java.util.List;

public interface SellerDao {

    void insert(Seller obj);
    void update(Seller obj);
    void deleteBuId(Integer obj);
    Seller findById(Integer obj);
    List<Seller> findAll();

}

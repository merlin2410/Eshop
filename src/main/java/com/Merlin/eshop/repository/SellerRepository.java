package com.Merlin.eshop.repository;

import com.Merlin.eshop.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    public Seller findByMobile(String mobile);

    public Seller findByEmail(String email);
}

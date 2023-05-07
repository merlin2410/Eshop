package com.Merlin.eshop.repository;

import com.Merlin.eshop.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    public Card findByCardNumber(String cardNumber);
}

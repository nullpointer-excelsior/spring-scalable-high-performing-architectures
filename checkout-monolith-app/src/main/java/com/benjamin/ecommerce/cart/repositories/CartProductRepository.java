package com.benjamin.ecommerce.cart.repositories;

import com.benjamin.ecommerce.cart.entities.CartProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {

    Optional<CartProductEntity> findByCartIdAndSku(Long cartId, String sku);

    @Transactional
    void deleteByCartIdAndSku(Long cartId,String sku);

}

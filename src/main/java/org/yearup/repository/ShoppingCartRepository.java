package org.yearup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yearup.models.CartItem;
import org.yearup.models.ShoppingCart;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<CartItem, Long>
{
    List<CartItem> findByUserId(Long userId);
    Optional<CartItem> findByUserIdAndProduct_ProductId(Long userId, Long productId);
    void deleteByUserId(Long userId);
}

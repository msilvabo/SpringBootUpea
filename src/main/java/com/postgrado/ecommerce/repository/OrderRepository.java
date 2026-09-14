package com.postgrado.ecommerce.repository;

import com.postgrado.ecommerce.dto.OrderItemDto;
import com.postgrado.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query( value = "SELECT sum(i.quantity*p.price) FROM public.order_items i"
            + " left join public.products p on p.id = i.product_id"
            + " where i.order_id = CAST(?1 AS uuid)"
            , nativeQuery = true)
    Double getTotalPriceNative(UUID id);

    @Query( value = "SELECT sum(i.quantity*p.price) FROM OrderItem i"
            + " left join i.product p "
            + " where i.order.id = ?1")
    Double getTotalPriceJPQL(UUID id);

    @Query("SELECT new com.postgrado.ecommerce.dto.OrderItemDto(p.id, p.name, i.quantity, p.price*i.quantity) FROM OrderItem i"
            + " left join i.product p "
            + " where i.order.id = ?1")
    List<OrderItemDto> getItemWithTotalPrice(UUID id);
}

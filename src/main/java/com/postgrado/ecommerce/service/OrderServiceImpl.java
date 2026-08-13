package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.entity.Order;
import com.postgrado.ecommerce.entity.OrderItem;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    ProductService productService;
    OrderRepository orderRepository;

    @Override
    public String create(OrderDto dto) {
        Order order = new Order();
        order.setComment(dto.getComment());
        List<OrderItem> items = dto.getItems().stream().map((itemDto)->{
                OrderItem item = new OrderItem();
                item.setQuantity(itemDto.getQuantity());
                Product product = productService.getById(itemDto.getProductId());
                item.setProduct(product);
                item.setOrder(order);
                return item;
                }).toList();
        order.setItems(items);
        Order orderSaved = orderRepository.save(order);
        //TODO: Set user from security
        return "Order saved succesfully";
    }
}

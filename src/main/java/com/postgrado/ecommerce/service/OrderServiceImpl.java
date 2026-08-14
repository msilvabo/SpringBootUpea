package com.postgrado.ecommerce.service;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.dto.OrderItemDto;
import com.postgrado.ecommerce.entity.Order;
import com.postgrado.ecommerce.entity.OrderItem;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.entity.User;
import com.postgrado.ecommerce.exception.EntityNotFoundException;
import com.postgrado.ecommerce.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(user);
        Order orderSaved = orderRepository.save(order);
        return "Order saved successful";
    }

    @Override
    public Double totalPrice(UUID id) {
        return orderRepository.getTotalPriceNative(id);
//        return orderRepository.getTotalPriceJPQL(id);
    }

    @Override
    public List<OrderItemDto> items(UUID id) {
        return orderRepository.getItemWithTotalPrice(id);
    }

    @Override
    public OrderDto getById(UUID id) {

        Order order = orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order", id));
        OrderDto orderDto = new OrderDto();
        orderDto.setComment(order.getComment());
        orderDto.setTotalPrice(orderRepository.getTotalPriceNative(id));
        orderDto.setState(order.getState());
        orderDto.setItems(orderRepository.getItemWithTotalPrice(id));

        return orderDto;
    }
}

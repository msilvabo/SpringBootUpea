package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.dto.OrderItemDto;
import com.postgrado.ecommerce.entity.Order;
import com.postgrado.ecommerce.entity.Product;
import com.postgrado.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name="bearerAuth")
@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> create (@RequestBody OrderDto order){
        String message = orderService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }


    public ResponseEntity<OrderDto> update(@PathVariable UUID id, @RequestBody OrderDto dto){
        orderService.updateOrder(id, dto);
        return ResponseEntity.ok().body(orderService.getById(id));
    }

    @GetMapping()
    public ResponseEntity<Page<OrderDto>> getOrders(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderDto> ordersPage = orderService.getOrders(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ordersPage);
    }

    @GetMapping("/total/{id}")
    public ResponseEntity<Double> totalPrice(@PathVariable UUID id){
        Double total = orderService.totalPrice(id);
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }
    @GetMapping("/items/{id}")
    public ResponseEntity<List<OrderItemDto>> items(@PathVariable UUID id){
        List<OrderItemDto> items = orderService.items(id);
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> order(@PathVariable UUID id){
        OrderDto orderDto = orderService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }
}

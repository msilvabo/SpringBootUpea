package com.postgrado.ecommerce.controller;

import com.postgrado.ecommerce.dto.OrderDto;
import com.postgrado.ecommerce.dto.OrderItemDto;
import com.postgrado.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
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

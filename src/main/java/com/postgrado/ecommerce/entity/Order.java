package com.postgrado.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime date = LocalDateTime.now();
    private String comment;
    @Enumerated(value = EnumType.STRING)
    private OrderState state = OrderState.PENDING;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    List<OrderItem> items;
}

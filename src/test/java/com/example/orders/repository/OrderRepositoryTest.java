package com.example.orders.repository;


import com.example.orders.models.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryTest {
    @InjectMocks
    OrderRepository orderRepository;

    Order order = new Order(null,"Вода", "Нет на складе", "Беларусь", "Завод", "NEW");

    @Test
    public void testAddToRepository(){

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orderRepository.addOrder(order);

        Assertions.assertNotNull(orders);
        Assertions.assertNotNull(orderRepository.getAll());
        Assertions.assertArrayEquals(orders.toArray(),orderRepository.getAll().toArray());
    }

    @Test
    public void testUpdateStatus(){
        orderRepository.addOrder(order);
        boolean result = orderRepository.updateOrderStatus(order.getId(),"IN_PROGRESS");
        Assertions.assertEquals(true,result);
    }
}
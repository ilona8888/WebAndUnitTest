package com.example.orders.operations;

import com.example.orders.models.OrderOperation;
import com.example.orders.repository.OrderRepository;
import com.example.orders.models.Order;

public class FinishOrderOperation implements OrderOperation {
    private final OrderRepository orderRepository;
    private Order order;

    public FinishOrderOperation(OrderRepository orderRepository, Order order) {
        this.order = order;
        this.orderRepository = orderRepository;
    }

    @Override
    public String execute() {
        if(orderRepository.updateOrderStatus(order.getId(),"DONE")){
            return "Заказ завершен";
        }else{
            return "Заказ не может быть завершен";
        }
    }
}

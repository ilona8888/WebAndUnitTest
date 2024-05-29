package com.example.orders.repository;

import org.springframework.stereotype.Component;
import com.example.orders.models.Order;

import java.util.ArrayList;
import java.util.List;

@Component("orderRepository")
public class OrderRepository {
    private List<Order> orderList = new ArrayList<>();

    public List<Order> getAll(){
        return orderList;
    }
    public Order addOrder(final Order order){
        try{
            if(order.getId() == null){
                order.setId(orderList.size());
            }
            orderList.add(order);
        }
        catch (Exception ex){

        }
        finally {
            return order;
        }
    }

    public boolean updateOrderStatus(Integer orderId, String status){
        try{
            orderList.get(orderId).setStatus(status);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
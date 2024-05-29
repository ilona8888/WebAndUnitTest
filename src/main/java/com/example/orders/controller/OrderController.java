package com.example.orders.controller;

import com.example.orders.models.Order;
import com.example.orders.models.OrderIterator;
import com.example.orders.operations.FinishOrderOperation;
import com.example.orders.operations.OrderOperationExecutor;
import com.example.orders.operations.StartExecuteOrderOperation;
import com.example.orders.data.AllOrdersResponse;
import com.example.orders.data.CreateOrderInputDTO;
import com.example.orders.repository.OrderRepository;
import lombok.NonNull;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderOperationExecutor orderOperationExecutor;

    public OrderController(OrderRepository orderRepository, OrderOperationExecutor orderOperationExecutor) {
        this.orderRepository = orderRepository;
        this.orderOperationExecutor = orderOperationExecutor;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllActiveUsers(){
        List<AllOrdersResponse> allUsersResponses = new ArrayList<>();
        var listOrders = orderRepository.getAll();

        var array = new Order[listOrders.size()];
        listOrders.toArray(array);

        OrderIterator<Order> uList = new OrderIterator<>(array);
        var iterator = uList.iterator();

        while (iterator.hasNext()) {
            Order order = iterator.next();
            allUsersResponses.add(new AllOrdersResponse(
                            order.getId(),
                            order.getLabel(),
                            order.getTarget(),
                            order.getProvider(),
                            order.getCount(),
                            order.getStatus()
                    )
            );
        }

        return ResponseEntity.ok(allUsersResponses);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createProject(@RequestBody @NonNull final CreateOrderInputDTO createOrderInputDTO)
    {
        final Order createdOrder = orderRepository.addOrder(new Order(null, createOrderInputDTO.getLabel(),
                createOrderInputDTO.getTarget(), createOrderInputDTO.getCount(), createOrderInputDTO.getProvider(),
                "NEW"));
        startOrderLifeCycle(createdOrder);
        System.out.println(ResponseEntity.ok(createdOrder));
        //return ResponseEntity.ok(createdOrder);
        return new ResponseEntity<>(createdOrder,HttpStatus.CREATED);
    }


    private void startOrderLifeCycle(Order order){
        new Thread(() -> {
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            orderOperationExecutor.executeOperation(
                                    new StartExecuteOrderOperation(orderRepository, order));
                        }
                    },
                    5000
            );

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            orderOperationExecutor.executeOperation(
                                    new FinishOrderOperation(orderRepository, order));
                        }
                    },
                    10000
            );
        }).start();
    }
}
package com.example.orders.operations;

import com.example.orders.models.OrderOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("orderOperationExecutor")
public class OrderOperationExecutor {

    private final List<OrderOperation> orderOperations
            = new ArrayList<>();

    public String executeOperation(OrderOperation orderOperation) {
        orderOperations.add(orderOperation);
        return orderOperation.execute();
    }
}

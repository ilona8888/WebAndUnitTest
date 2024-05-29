package com.example.orders.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class Order {

    private Integer id;

    private String label;

    private String target;

    private String count;

    private String provider;

    private String status;

    public Order(Integer id, String label, String target, String count, String provider, String status) {
        this.id = id;
        this.label = label;
        this.target = target;
        this.count = count;
        this.provider = provider;
        this.status = status;
    }
}

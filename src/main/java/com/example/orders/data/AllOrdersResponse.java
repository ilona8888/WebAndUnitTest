package com.example.orders.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AllOrdersResponse {
    private Integer id;

    private String label;

    private String target;

    private String provider;

    private String count;

    private String status;
}